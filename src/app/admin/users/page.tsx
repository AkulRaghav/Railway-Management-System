"use client";
import { useEffect, useState } from "react";
import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";
import { motion } from "framer-motion";
import { toast } from "sonner";
import { Users, Shield, Ban, CheckCircle } from "lucide-react";
import { Navbar } from "@/components/ui/navbar";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Modal } from "@/components/ui/modal";
import { Badge } from "@/components/ui/badge";
import { Skeleton } from "@/components/ui/skeleton";

interface UserData { id: string; name: string; email: string; phone: string | null; role: string; isActive: boolean; createdAt: string; _count: { bookings: number } }

export default function AdminUsersPage() {
  const { data: session, status } = useSession();
  const router = useRouter();
  const [users, setUsers] = useState<UserData[]>([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [confirmAction, setConfirmAction] = useState<{ userId: string; action: string; value?: string } | null>(null);

  useEffect(() => {
    if (status === "unauthenticated") router.push("/login");
    if (status === "authenticated") {
      if ((session?.user as { role?: string })?.role !== "ADMIN") { router.push("/dashboard"); return; }
      loadUsers();
    }
  }, [status, session, router]);

  const loadUsers = () => { fetch("/api/admin/users").then(r => r.json()).then(d => setUsers(Array.isArray(d) ? d : [])).finally(() => setLoading(false)); };

  const executeAction = async () => {
    if (!confirmAction) return;
    const res = await fetch("/api/admin/users", { method: "PATCH", headers: { "Content-Type": "application/json" }, body: JSON.stringify(confirmAction) });
    const data = await res.json();
    if (res.ok) { toast.success(data.message); loadUsers(); } else toast.error(data.error);
    setConfirmAction(null);
  };

  const filtered = users.filter(u => u.name.toLowerCase().includes(search.toLowerCase()) || u.email.toLowerCase().includes(search.toLowerCase()));

  return (
    <div className="min-h-screen bg-[#0B1120]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }}>
          <h1 className="text-2xl font-bold text-slate-100 flex items-center gap-2 mb-6"><Users size={24} className="text-amber-500" />User Management</h1>
          <Input placeholder="Search by name or email..." value={search} onChange={e => setSearch(e.target.value)} className="mb-4 max-w-md" />

          {loading ? <div className="space-y-3">{[1,2,3].map(i => <Skeleton key={i} className="h-16" />)}</div> : (
            <div className="overflow-x-auto rounded-xl border border-slate-800">
              <table className="w-full text-sm">
                <thead className="bg-slate-800/60"><tr className="text-left text-slate-400">
                  <th className="px-4 py-3">Name</th><th className="px-4 py-3">Email</th><th className="px-4 py-3">Role</th><th className="px-4 py-3">Status</th><th className="px-4 py-3">Bookings</th><th className="px-4 py-3">Joined</th><th className="px-4 py-3">Actions</th>
                </tr></thead>
                <tbody className="divide-y divide-slate-800">
                  {filtered.map(u => (
                    <tr key={u.id} className="hover:bg-slate-800/30">
                      <td className="px-4 py-3 text-slate-200">{u.name}</td>
                      <td className="px-4 py-3 text-slate-400">{u.email}</td>
                      <td className="px-4 py-3"><Badge variant={u.role === "ADMIN" ? "warning" : "default"}>{u.role}</Badge></td>
                      <td className="px-4 py-3"><Badge variant={u.isActive ? "success" : "danger"}>{u.isActive ? "Active" : "Suspended"}</Badge></td>
                      <td className="px-4 py-3 text-slate-400">{u._count.bookings}</td>
                      <td className="px-4 py-3 text-slate-500 text-xs">{new Date(u.createdAt).toLocaleDateString()}</td>
                      <td className="px-4 py-3 flex gap-2">
                        <Button size="sm" variant="ghost" onClick={() => setConfirmAction({ userId: u.id, action: "changeRole", value: u.role === "ADMIN" ? "PASSENGER" : "ADMIN" })}>
                          <Shield size={14} className="mr-1" />{u.role === "ADMIN" ? "Demote" : "Promote"}
                        </Button>
                        <Button size="sm" variant="ghost" onClick={() => setConfirmAction({ userId: u.id, action: "toggleActive" })}>
                          {u.isActive ? <><Ban size={14} className="mr-1" />Suspend</> : <><CheckCircle size={14} className="mr-1" />Activate</>}
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </motion.div>

        <Modal isOpen={!!confirmAction} onClose={() => setConfirmAction(null)} title="Confirm Action">
          <p className="text-slate-400 text-sm mb-4">
            {confirmAction?.action === "changeRole" ? `Change this user's role to ${confirmAction?.value}?` : "Toggle this user's active status?"}
          </p>
          <div className="flex gap-3"><Button variant="secondary" onClick={() => setConfirmAction(null)} className="flex-1">Cancel</Button><Button onClick={executeAction} className="flex-1">Confirm</Button></div>
        </Modal>
      </main>
    </div>
  );
}
