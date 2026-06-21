"use client";

import { useEffect, useState } from "react";
import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";
import { motion } from "framer-motion";
import { Shield, Filter, Users, Ticket, Train } from "lucide-react";

import { Navbar } from "@/components/ui/navbar";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Input } from "@/components/ui/input";
import { Select } from "@/components/ui/select";
import { Skeleton } from "@/components/ui/skeleton";

interface AdminBooking {
  id: string;
  pnr: string;
  passengerName: string;
  source: string;
  destination: string;
  journeyDate: string;
  classBooked: string;
  status: string;
  fare: string;
  createdAt: string;
  user: { name: string; email: string };
  train: { trainName: string; trainNumber: string };
}

export default function AdminPage() {
  const { data: session, status } = useSession();
  const router = useRouter();
  const [bookings, setBookings] = useState<AdminBooking[]>([]);
  const [loading, setLoading] = useState(true);
  const [statusFilter, setStatusFilter] = useState("");
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    if (status === "unauthenticated") router.push("/login");
    if (status === "authenticated") {
      const role = (session?.user as { role?: string })?.role;
      if (role !== "ADMIN") {
        router.push("/dashboard");
        return;
      }
      fetch("/api/bookings/admin")
        .then((r) => r.json())
        .then((data) => { setBookings(Array.isArray(data) ? data : []); setLoading(false); })
        .catch(() => setLoading(false));
    }
  }, [status, session, router]);

  const filtered = bookings.filter((b) => {
    const matchesStatus = !statusFilter || b.status === statusFilter;
    const q = searchQuery.toLowerCase();
    const matchesQuery = !q || b.pnr.includes(q) || b.passengerName.toLowerCase().includes(q) || b.user.email.toLowerCase().includes(q);
    return matchesStatus && matchesQuery;
  });

  const statusVariant = (s: string) => {
    switch (s) { case "CONFIRMED": return "success"; case "PENDING_PAYMENT": return "warning"; case "CANCELLED": return "danger"; case "WAITLISTED": return "info"; default: return "default"; }
  };

  const stats = {
    total: bookings.length,
    confirmed: bookings.filter(b => b.status === "CONFIRMED").length,
    pending: bookings.filter(b => b.status === "PENDING_PAYMENT").length,
    cancelled: bookings.filter(b => b.status === "CANCELLED").length,
  };

  return (
    <div className="min-h-screen bg-[#0B1120]">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ ease: [0.16, 1, 0.3, 1] }}>
          <div className="flex items-center gap-3 mb-6">
            <Shield size={24} className="text-amber-500" />
            <h1 className="text-2xl font-bold text-slate-100">Admin Dashboard</h1>
          </div>

          {/* Stats */}
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-8">
            {[
              { label: "Total Bookings", value: stats.total, icon: Ticket },
              { label: "Confirmed", value: stats.confirmed, icon: Ticket },
              { label: "Pending Payment", value: stats.pending, icon: Ticket },
              { label: "Cancelled", value: stats.cancelled, icon: Ticket },
            ].map((s) => (
              <Card key={s.label} variant="default">
                <CardContent className="flex items-center gap-3 p-4">
                  <div className="text-amber-500"><s.icon size={20} /></div>
                  <div>
                    <p className="text-2xl font-bold text-slate-100">{s.value}</p>
                    <p className="text-xs text-slate-500">{s.label}</p>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>

          {/* Filters */}
          <Card variant="default" className="mb-6">
            <CardContent className="flex flex-col sm:flex-row gap-3 p-4">
              <Input
                placeholder="Search by PNR, name, or email..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="flex-1"
              />
              <Select
                options={[
                  { value: "", label: "All Statuses" },
                  { value: "CONFIRMED", label: "Confirmed" },
                  { value: "PENDING_PAYMENT", label: "Pending Payment" },
                  { value: "CANCELLED", label: "Cancelled" },
                  { value: "WAITLISTED", label: "Waitlisted" },
                ]}
                value={statusFilter}
                onChange={(e) => setStatusFilter(e.target.value)}
                className="w-48"
              />
            </CardContent>
          </Card>

          {/* Table */}
          {loading ? (
            <div className="space-y-3">{[1, 2, 3, 4].map(i => <Skeleton key={i} className="h-16 w-full" />)}</div>
          ) : filtered.length === 0 ? (
            <Card variant="default" className="text-center py-12">
              <CardContent>
                <p className="text-slate-400">No bookings match your filters.</p>
              </CardContent>
            </Card>
          ) : (
            <div className="overflow-x-auto rounded-xl border border-slate-800">
              <table className="w-full text-sm">
                <thead className="bg-slate-800/60">
                  <tr className="text-left text-slate-400">
                    <th className="px-4 py-3 font-medium">PNR</th>
                    <th className="px-4 py-3 font-medium">Passenger</th>
                    <th className="px-4 py-3 font-medium">Train</th>
                    <th className="px-4 py-3 font-medium">Route</th>
                    <th className="px-4 py-3 font-medium">Date</th>
                    <th className="px-4 py-3 font-medium">Fare</th>
                    <th className="px-4 py-3 font-medium">Status</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-slate-800">
                  {filtered.map((b) => (
                    <tr key={b.id} className="hover:bg-slate-800/30 transition-colors">
                      <td className="px-4 py-3 font-mono text-amber-400">{b.pnr}</td>
                      <td className="px-4 py-3">
                        <p className="text-slate-200">{b.passengerName}</p>
                        <p className="text-xs text-slate-500">{b.user.email}</p>
                      </td>
                      <td className="px-4 py-3 text-slate-300">{b.train.trainName}</td>
                      <td className="px-4 py-3 text-slate-300">{b.source} → {b.destination}</td>
                      <td className="px-4 py-3 text-slate-400">{new Date(b.journeyDate).toLocaleDateString()}</td>
                      <td className="px-4 py-3 text-slate-200">₹{b.fare}</td>
                      <td className="px-4 py-3"><Badge variant={statusVariant(b.status)}>{b.status.replace("_", " ")}</Badge></td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </motion.div>
      </main>
    </div>
  );
}
