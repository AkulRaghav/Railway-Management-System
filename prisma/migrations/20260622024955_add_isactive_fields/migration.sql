-- RedefineTables
PRAGMA defer_foreign_keys=ON;
PRAGMA foreign_keys=OFF;
CREATE TABLE "new_Train" (
    "id" TEXT NOT NULL PRIMARY KEY,
    "trainNumber" TEXT NOT NULL,
    "trainName" TEXT NOT NULL,
    "source" TEXT NOT NULL,
    "destination" TEXT NOT NULL,
    "departureTime" TEXT NOT NULL,
    "arrivalTime" TEXT NOT NULL,
    "duration" TEXT NOT NULL,
    "totalSeats" INTEGER NOT NULL,
    "fare" DECIMAL NOT NULL,
    "isActive" BOOLEAN NOT NULL DEFAULT true
);
INSERT INTO "new_Train" ("arrivalTime", "departureTime", "destination", "duration", "fare", "id", "source", "totalSeats", "trainName", "trainNumber") SELECT "arrivalTime", "departureTime", "destination", "duration", "fare", "id", "source", "totalSeats", "trainName", "trainNumber" FROM "Train";
DROP TABLE "Train";
ALTER TABLE "new_Train" RENAME TO "Train";
CREATE UNIQUE INDEX "Train_trainNumber_key" ON "Train"("trainNumber");
CREATE TABLE "new_User" (
    "id" TEXT NOT NULL PRIMARY KEY,
    "email" TEXT NOT NULL,
    "passwordHash" TEXT,
    "name" TEXT NOT NULL,
    "phone" TEXT,
    "image" TEXT,
    "role" TEXT NOT NULL DEFAULT 'PASSENGER',
    "isActive" BOOLEAN NOT NULL DEFAULT true,
    "createdAt" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO "new_User" ("createdAt", "email", "id", "image", "name", "passwordHash", "phone", "role") SELECT "createdAt", "email", "id", "image", "name", "passwordHash", "phone", "role" FROM "User";
DROP TABLE "User";
ALTER TABLE "new_User" RENAME TO "User";
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");
PRAGMA foreign_keys=ON;
PRAGMA defer_foreign_keys=OFF;
