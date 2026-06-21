# 🚂 RailOne — Premium Railway Reservation Platform

A modern, production-grade railway reservation web application built with Next.js 16, featuring 3D visualizations, real-time payment processing, and a comprehensive booking system.

![Next.js](https://img.shields.io/badge/Next.js-16-black?logo=next.js)
![TypeScript](https://img.shields.io/badge/TypeScript-Strict-blue?logo=typescript)
![Prisma](https://img.shields.io/badge/Prisma-ORM-2D3748?logo=prisma)
![Tailwind](https://img.shields.io/badge/Tailwind-CSS-38B2AC?logo=tailwindcss)
![Three.js](https://img.shields.io/badge/Three.js-3D-black?logo=three.js)

## ✨ Features

- **3D Login Scene** — Animated low-poly trains on dual parallel tracks with smoke particles, parallax mountains, and cinematic camera intro
- **3D Route Visualizer** — Interactive globe → India map transition with real geographic data, glowing route lines, and city markers
- **Multi-step Booking Wizard** — Search trains → filter/sort → select class → seat map → passenger details → fare breakdown → payment
- **Razorpay Payment Integration** — Real test-mode checkout with server-side signature verification
- **Google OAuth** — One-click sign in with Google
- **Forgot Password with Email OTP** — Real 6-digit OTP sent via Gmail SMTP
- **PNR Enquiry** — Boarding-pass style status cards
- **Ticket Cancellation** — Automated refund calculation based on cancellation policy
- **Live Train Tracking** — Simulated real-time status with route timeline
- **Admin Dashboard** — Role-gated booking management with filters
- **285 Seeded Trains** — Realistic Indian Railways data across 15 cities
- **Aadhar Encryption** — AES-256 at rest, masked display everywhere
- **Fully Responsive** — Mobile-first design with dark theme

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Framework | Next.js 16 (App Router) |
| Language | TypeScript (strict) |
| Styling | Tailwind CSS |
| 3D/Animation | Three.js, @react-three/fiber, Framer Motion |
| Database | SQLite via Prisma ORM (swap to PostgreSQL for production) |
| Auth | NextAuth.js (Credentials + Google OAuth) |
| Payments | Razorpay (test mode) |
| Email | Nodemailer (Gmail SMTP) |
| Validation | Zod + React Hook Form |
| Notifications | Sonner (toast) |

## 🚀 Getting Started

### Prerequisites
- Node.js 18+
- npm

### Setup

```bash
# Clone the repo
git clone https://github.com/AkulRaghav/Railway-Management-System.git
cd Railway-Management-System

# Install dependencies
npm install

# Copy environment variables
cp .env.example .env
# Edit .env with your actual keys (see below)

# Set up database
npx prisma migrate dev --name init
npx prisma db seed

# Start development server
npm run dev
```

Open [http://localhost:3000](http://localhost:3000)

### Environment Variables

See `.env.example` for all required variables. You'll need:
- **Razorpay** test keys (from [dashboard.razorpay.com](https://dashboard.razorpay.com))
- **Google OAuth** credentials (from [Google Cloud Console](https://console.cloud.google.com/apis/credentials))
- **Gmail App Password** for OTP emails (from [myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords))

### Demo Credentials

```
User:  demo@railone.in / password123
Admin: admin@railone.in / admin123
```

## 📂 Project Structure

```
├── src/
│   ├── app/              # Next.js App Router pages & API routes
│   │   ├── login/        # 3D login with Google OAuth
│   │   ├── register/     # Registration
│   │   ├── forgot-password/ # Email OTP reset flow
│   │   ├── dashboard/    # User home
│   │   ├── book-ticket/  # Multi-step booking wizard + 3D globe
│   │   ├── payment/      # Razorpay checkout
│   │   ├── my-bookings/  # Booking history
│   │   ├── pnr-enquiry/  # PNR status lookup
│   │   ├── cancellation/ # Ticket cancellation
│   │   ├── track-train/  # Live train status
│   │   ├── admin/        # Admin dashboard
│   │   └── api/          # REST API routes
│   ├── components/
│   │   ├── 3d/           # Three.js scenes (LoginScene, RouteGlobe)
│   │   ├── ui/           # Design system (Button, Card, Input, etc.)
│   │   └── booking/      # Booking-specific components
│   └── lib/              # Utilities (auth, prisma, encryption, email)
├── prisma/
│   ├── schema.prisma     # Database schema
│   └── seed.ts           # 285 trains across 15 Indian cities
└── public/
```

## 🔒 Security

- Passwords hashed with bcrypt (12 rounds)
- Aadhar numbers encrypted with AES-256-CBC at rest
- Server-side session validation on all protected routes
- Razorpay signature verification before booking confirmation
- OTP expires in 10 minutes, single-use
- CSRF protection via NextAuth

## 📜 License

MIT

---

Built by [Akul Raghav](https://github.com/AkulRaghav)
