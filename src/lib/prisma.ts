import { PrismaClient } from "@prisma/client";

const globalForPrisma = globalThis as unknown as {
  prisma: PrismaClient | undefined;
};

function getClient(): PrismaClient {
  if (typeof window !== "undefined") {
    // Should never be called client-side, but guard anyway
    throw new Error("PrismaClient cannot be used on the client");
  }

  if (process.env.VERCEL) {
    // eslint-disable-next-line @typescript-eslint/no-require-imports
    const fs = require("fs");
    // eslint-disable-next-line @typescript-eslint/no-require-imports
    const path = require("path");
    const tmpDb = "/tmp/railone.db";
    if (!fs.existsSync(tmpDb)) {
      const publicDb = path.join(process.cwd(), "public", "railone.db");
      if (fs.existsSync(publicDb)) {
        fs.copyFileSync(publicDb, tmpDb);
      }
    }
    return new PrismaClient({
      datasources: { db: { url: `file:${tmpDb}` } },
    });
  }

  return new PrismaClient();
}

export const prisma = globalForPrisma.prisma ?? getClient();

if (process.env.NODE_ENV !== "production") globalForPrisma.prisma = prisma;
