<p align="center">
  <img src="https://img.shields.io/badge/🚂_RailOne-Premium_Railway_Platform-f59e0b?style=for-the-badge&labelColor=0B1120" alt="RailOne" />
</p>

<h1 align="center">RailOne — Premium Railway Reservation Platform</h1>

<p align="center">
  <em>A modern, production-grade railway reservation web application featuring 3D visualizations, real-time payments, and comprehensive booking management — built to the standards of a YC-funded startup product.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Next.js-16.2-000000?logo=next.js&logoColor=white" alt="Next.js" />
  <img src="https://img.shields.io/badge/TypeScript-Strict-3178C6?logo=typescript&logoColor=white" alt="TypeScript" />
  <img src="https://img.shields.io/badge/Three.js-3D-000000?logo=three.js&logoColor=white" alt="Three.js" />
  <img src="https://img.shields.io/badge/Prisma-ORM-2D3748?logo=prisma&logoColor=white" alt="Prisma" />
  <img src="https://img.shields.io/badge/Tailwind_CSS-4.0-38B2AC?logo=tailwindcss&logoColor=white" alt="Tailwind" />
  <img src="https://img.shields.io/badge/Razorpay-Payments-0C2451?logo=razorpay&logoColor=white" alt="Razorpay" />
  <img src="https://img.shields.io/badge/Framer_Motion-Animations-0055FF?logo=framer&logoColor=white" alt="Framer Motion" />
  <img src="https://img.shields.io/badge/NextAuth-OAuth-000000?logo=next.js&logoColor=white" alt="NextAuth" />
</p>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-complete-tech-stack)
- [Architecture](#-architecture)
- [Pages & Routes](#-pages--routes)
- [API Endpoints](#-api-endpoints)
- [Database Schema](#-database-schema)
- [3D Visualizations](#-3d-visualizations)
- [Security](#-security-implementation)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [Project Structure](#-project-structure)
- [Screenshots](#-screenshots)
- [License](#-license)

---

## 🌟 Overview

RailOne is a **complete re-platform** of a legacy Java Swing + JDBC railway reservation desktop application into a modern, full-stack web application. It preserves and significantly enhances every original feature (search trains, book tickets, pay fares, cancel tickets, PNR enquiry) while adding production-grade security, beautiful 3D visualizations, and a premium dark-theme UI.

### What Makes This Different

| Aspect | Old System (Java Swing) | RailOne (Next.js) |
|--------|------------------------|-------------------|
| Platform | Desktop-only (JFrame) | Web (any device, any browser) |
| Database | H2/MySQL local | SQLite/PostgreSQL with Prisma ORM |
| UI Framework | Java Swing (null layout) | Tailwind CSS + Framer Motion |
| Visualizations | None | Three.js 3D scenes |
| Authentication | None | NextAuth (Google OAuth + Credentials) |
| Payments | Simulated | Razorpay (real test-mode) |
| Security | Plaintext Aadhar | AES-256 encryption, bcrypt, CSRF |
| Responsiveness | Fixed window | Mobile-first responsive |
| State Management | Swing events | React Hook Form + Zod validation |
| Deployment | Local JVM | Vercel/Node.js (cloud-ready) |

---

## ✨ Features

### Core Booking Features

| Feature | Description | Route |
|---------|-------------|-------|
| **Train Search** | Search 285+ trains across 15 Indian cities with real-time results | `/book-ticket` |
| **Filters & Sorting** | Filter by departure time (Early/Morning/Afternoon/Evening/Night), sort by price/duration/departure | `/book-ticket` |
| **Seat Selection** | Visual seat map with color-coded availability (Available/Booked/Selected) | `/book-ticket` |
| **Multi-step Booking** | 4-step wizard: Search → Select → Passenger Details → Review & Pay | `/book-ticket` |
| **Fare Breakdown** | Transparent pricing: Base Fare + GST (5%) + Convenience Fee | `/book-ticket` |
| **Waitlist Support** | Book into waitlisted status when classes are full, with urgency badges ("Only X left!") | `/book-ticket` |
| **PNR Enquiry** | Boarding-pass style result card with route visualization | `/pnr-enquiry` |
| **Ticket Cancellation** | Refund policy: >48h = 90%, 24-48h = 50%, <24h = 0% | `/cancellation` |
| **My Bookings** | Full booking history with status badges and quick actions | `/my-bookings` |
| **Live Train Tracking** | Simulated real-time status with progress bar and station timeline | `/track-train` |

### Authentication & Security

| Feature | Description |
|---------|-------------|
| **Email/Password Login** | Credentials-based auth with bcrypt password hashing |
| **Google OAuth** | One-click "Continue with Google" sign-in |
| **Registration** | Full sign-up flow with phone, email, password validation |
| **Forgot Password** | Real email OTP (6-digit code via Gmail SMTP, 10-min expiry) |
| **Session Management** | JWT-based sessions via NextAuth.js |
| **Role-based Access** | PASSENGER and ADMIN roles with route-level guards |

### Payment Integration

| Feature | Description |
|---------|-------------|
| **Razorpay Checkout** | Real payment modal (Card/UPI/Net Banking) |
| **Server-side Validation** | Amount verified from database, not client input |
| **Signature Verification** | HMAC-SHA256 signature check before confirming bookings |
| **E-Ticket Generation** | Confirmation screen with full ticket details and PNR |
| **Idempotent Payments** | Duplicate payment prevention built-in |

### Admin Features

| Feature | Description | Route |
|---------|-------------|-------|
| **Admin Dashboard** | Overview stats (total/confirmed/pending/cancelled bookings) | `/admin` |
| **Booking Table** | All bookings across all users with search and status filters | `/admin` |
| **Role Gating** | Only `ADMIN` role users can access, enforced server-side | `/admin` |

### 3D Visualizations

| Scene | Description | Location |
|-------|-------------|----------|
| **Login Scene** | Dual-track animated trains, parallax mountains, smoke particles, starfield, cinematic camera intro | `/login` |
| **Route Globe** | Rotating stylized Earth → zoom into India map with real geographic outline, glowing route lines, city markers | `/book-ticket` |

---

## 🔧 Complete Tech Stack

| Category | Technology | Version | Purpose |
|----------|-----------|---------|---------|
| **Framework** | Next.js | 16.2.9 | Full-stack React framework (App Router) |
| **Language** | TypeScript | 5.x | Static typing (strict mode) |
| **Styling** | Tailwind CSS | 4.x | Utility-first CSS framework |
| **3D Engine** | Three.js | 0.184 | WebGL 3D rendering |
| **3D React** | @react-three/fiber | 9.x | React renderer for Three.js |
| **3D Helpers** | @react-three/drei | 10.x | Pre-built 3D components (Stars, Text, RoundedBox, Line) |
| **Animation** | Framer Motion | 12.x | Page transitions, micro-interactions |
| **Database** | SQLite (dev) / PostgreSQL (prod) | - | Relational database |
| **ORM** | Prisma | 6.19 | Type-safe database client + migrations |
| **Auth** | NextAuth.js | 4.x | Authentication (Credentials + Google OAuth) |
| **Payments** | Razorpay | - | Payment gateway (test mode) |
| **Email** | Nodemailer | - | SMTP email sending (Gmail) |
| **Forms** | React Hook Form | 7.x | Performant form state management |
| **Validation** | Zod | 4.x | Schema validation (client + server) |
| **Tables** | TanStack Table | 8.x | Headless table logic |
| **Icons** | Lucide React | 1.x | Icon library (200+ icons) |
| **Toasts** | Sonner | 2.x | Toast notification system |
| **Utilities** | clsx + tailwind-merge | - | Conditional class merging |
| **Encryption** | Node.js crypto | - | AES-256-CBC for Aadhar encryption |
| **Hashing** | bcryptjs | 3.x | Password hashing (12 rounds) |

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        CLIENT (Browser)                       │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │  Login   │  │  Booking │  │  Payment │  │  Admin   │   │
│  │  3D Scene│  │  Wizard  │  │  Checkout│  │Dashboard │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
│       │              │              │              │         │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              React + Next.js App Router              │   │
│  │         (TypeScript, Tailwind, Framer Motion)       │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                              │
                    ┌─────────┴─────────┐
                    │   API Routes       │
                    │  (Server-side)     │
                    └─────────┬─────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
┌───────┴───────┐   ┌────────┴────────┐   ┌───────┴───────┐
│   NextAuth    │   │  Prisma ORM     │   │   Razorpay    │
│ (JWT Sessions)│   │  (DB Queries)   │   │   (Payments)  │
└───────┬───────┘   └────────┬────────┘   └───────┬───────┘
        │                     │                     │
┌───────┴───────┐   ┌────────┴────────┐   ┌───────┴───────┐
│ Google OAuth  │   │ SQLite/Postgres │   │  Razorpay API │
│ Gmail SMTP    │   │   (Database)    │   │  (External)   │
└───────────────┘   └─────────────────┘   └───────────────┘
```

---

## 📄 Pages & Routes

| Route | Page | Auth Required | Description |
|-------|------|:---:|-------------|
| `/` | Root | No | Redirects to `/login` |
| `/login` | Login | No | 3D animated scene + email/password + Google OAuth |
| `/register` | Register | No | Account creation with validation |
| `/forgot-password` | Password Reset | No | 3-step OTP flow (email → verify → reset) |
| `/dashboard` | Dashboard | ✅ | User home, quick actions, recent bookings |
| `/book-ticket` | Booking Wizard | ✅ | 4-step train search & booking with 3D globe |
| `/payment/[bookingId]` | Payment | ✅ | Razorpay checkout + e-ticket confirmation |
| `/my-bookings` | Booking History | ✅ | All user bookings with actions |
| `/pnr-enquiry` | PNR Status | ✅ | Boarding-pass style PNR lookup |
| `/cancellation` | Cancel Ticket | ✅ | PNR-based cancellation with refund calc |
| `/track-train` | Live Status | ✅ | Train tracking with route timeline |
| `/admin` | Admin Panel | ✅ (ADMIN) | All bookings table with filters |

---

## 🔌 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/callback/credentials` | Email/password login |
| `POST` | `/api/auth/callback/google` | Google OAuth callback |
| `POST` | `/api/auth/register` | Create new account |
| `POST` | `/api/auth/forgot-password` | Send OTP to email |
| `POST` | `/api/auth/verify-otp` | Verify 6-digit OTP |
| `POST` | `/api/auth/reset-password` | Set new password after OTP |

### Trains

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/trains?source=X&destination=Y` | Search trains by route |

### Bookings

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/bookings` | Get user's bookings |
| `POST` | `/api/bookings` | Create new booking |
| `GET` | `/api/bookings/admin` | Get all bookings (admin only) |

### Payments

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/payments` | Legacy payment (fallback) |
| `POST` | `/api/payments/create-order` | Create Razorpay order |
| `POST` | `/api/payments/verify` | Verify payment signature |

### Cancellations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/cancellations?pnr=X` | Look up booking by PNR |
| `POST` | `/api/cancellations` | Cancel a booking |

---

## 🗄️ Database Schema

### Entity Relationship Diagram

```
User (1) ──── (N) Booking (1) ──── (0..1) Payment
  │                   │
  │                   └──── (0..1) Cancellation
  │
  └──── (N) Account (OAuth providers)

Train (1) ──── (N) TrainClass
  │
  └──── (N) Booking

Otp (standalone, indexed by email+code)
```

### Models

| Model | Fields | Purpose |
|-------|--------|---------|
| **User** | id, email, passwordHash?, name, phone?, image?, role, createdAt | User accounts (supports OAuth without password) |
| **Account** | id, userId, type, provider, providerAccountId, tokens... | OAuth provider links (Google) |
| **Train** | id, trainNumber, trainName, source, destination, times, duration, totalSeats, fare | Train master data |
| **TrainClass** | id, trainId, className, fareMultiplier, seatsAvailable | Class options per train (General → AC First Class) |
| **Booking** | id, pnr, userId, trainId, passenger details, route, journeyDate, class, seat, status, fare | Individual ticket bookings |
| **Payment** | id, bookingId, amount, status, transactionId, paidAt | Payment records (linked to Razorpay) |
| **Cancellation** | id, bookingId, reason, cancelledAt, refundAmount, refundStatus | Cancellation records with refund tracking |
| **Otp** | id, email, code, expiresAt, used | One-time passwords for forgot-password flow |

### Enums

| Enum | Values |
|------|--------|
| **Role** | `PASSENGER`, `ADMIN` |
| **Gender** | `MALE`, `FEMALE`, `OTHER` |
| **BookingStatus** | `PENDING_PAYMENT`, `CONFIRMED`, `CANCELLED`, `WAITLISTED` |
| **PaymentStatus** | `PENDING`, `SUCCESS`, `FAILED`, `REFUNDED` |

### Seed Data

| Category | Count | Details |
|----------|-------|---------|
| **Cities** | 15 | Mumbai, Delhi, Bangalore, Chennai, Kolkata, Hyderabad, Pune, Ahmedabad, Jaipur, Lucknow, Chandigarh, Goa, Kochi, Bhopal, Patna |
| **Trains** | ~285 | All city-pair combinations, high-traffic routes get 3-5 trains |
| **Train Types** | 15 | Rajdhani, Shatabdi, Duronto, Garib Rath, Jan Shatabdi, Superfast, Intercity, Humsafar, Tejas, Vande Bharat, Sampark Kranti, etc. |
| **Classes/Train** | 3-5 | General, Sleeper, AC 3-Tier, AC 2-Tier, AC First Class |
| **Demo Users** | 2 | Passenger + Admin accounts |

---

## 🎨 3D Visualizations

### Login Scene (`/login`)

| Component | Technology | Description |
|-----------|-----------|-------------|
| **Locomotive** | RoundedBox + custom geometry | Realistic low-poly engine with rounded body, cab windshield, smokestack, headlight, wheels with axles |
| **Coaches** | RoundedBox arrays | 3 trailing coaches per train with lit window rows |
| **Smoke Particles** | THREE.Points + per-frame simulation | 50 particles per train, direction-aware drift, sine-based sway |
| **Dual Tracks** | CatmullRomCurve3 + arc-length spacing | Two parallel tracks, trains move opposite directions, coaches follow the curve |
| **Mountains** | PlaneGeometry + vertex displacement | 3-layer parallax with procedural sin/cos noise |
| **Camera Intro** | useFrame lerp + expo-out easing | Top-down → eye-level cinematic intro (1.8s hold + 2.5s transition) |
| **Performance** | Reduced motion, particle count limits | Respects `prefers-reduced-motion`, caps DPR |

### Route Globe (`/book-ticket`)

| State | Visual | Trigger |
|-------|--------|---------|
| **Default** | Rotating textured Earth with procedural continent shapes, atmosphere rim, starfield | No cities selected |
| **Transition** | Smooth camera zoom from globe → India close-up (expo-out lerp) | Both From & To selected |
| **India Map** | 151-point real geographic outline (from OSM/Natural Earth data), 69 state border polylines, extruded 3D shape | Both cities selected |
| **Route Line** | Quadratic Bezier arc with animated traveling pulse | Both cities selected |
| **City Markers** | Pulsing amber spheres + floating text labels at real lat/lng positions | Both cities selected |

---

## 🔒 Security Implementation

| Security Measure | Implementation | Details |
|-----------------|----------------|---------|
| **Password Hashing** | bcryptjs (12 rounds) | Passwords never stored in plaintext |
| **Aadhar Encryption** | AES-256-CBC | Encrypted at rest, key from env var, only displays masked (XXXX-XXXX-1234) |
| **Session Security** | JWT via NextAuth | Server-side session validation on all protected routes |
| **OAuth** | Google provider | Token-based, no password stored for OAuth users |
| **Payment Verification** | HMAC-SHA256 | Server-side Razorpay signature verification before confirming bookings |
| **OTP Security** | 6-digit, 10-min expiry, single-use | Invalidates previous OTPs on new request |
| **Input Validation** | Zod schemas | Client + server-side validation (Aadhar: `/^\d{12}$/`, Phone: `/^\d{10}$/`) |
| **CSRF Protection** | NextAuth defaults | Token-based CSRF mitigation |
| **Amount Validation** | Server-side DB lookup | Payment amount fetched from database, never trusted from client |
| **Role Guards** | Server-side middleware | Admin routes check `role === "ADMIN"` before returning data |
| **Secrets Management** | Environment variables | All keys/secrets in `.env`, never hardcoded |

---

## 🚀 Getting Started

### Prerequisites

- **Node.js** 18+ ([download](https://nodejs.org/))
- **npm** (comes with Node.js)
- **Git** ([download](https://git-scm.com/))

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/AkulRaghav/Railway-Management-System.git
cd Railway-Management-System

# 2. Install dependencies
npm install

# 3. Set up environment variables
cp .env.example .env
# Edit .env with your actual credentials (see table below)

# 4. Initialize database & seed data
npx prisma migrate dev --name init
npx prisma db seed

# 5. Start development server
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) in your browser.

### Demo Credentials

| Role | Email | Password |
|------|-------|----------|
| Passenger | `demo@railone.in` | `password123` |
| Admin | `admin@railone.in` | `admin123` |

---

## 🔑 Environment Variables

| Variable | Required | Source | Description |
|----------|:---:|--------|-------------|
| `DATABASE_URL` | ✅ | Local | Database connection string (`file:./dev.db` for SQLite) |
| `NEXTAUTH_SECRET` | ✅ | Generate | Random string for JWT signing (`openssl rand -base64 32`) |
| `NEXTAUTH_URL` | ✅ | Local | App URL (`http://localhost:3000`) |
| `AADHAR_ENCRYPTION_KEY` | ✅ | Generate | 32-character key for AES-256 encryption |
| `RAZORPAY_KEY_ID` | ✅ | [Razorpay Dashboard](https://dashboard.razorpay.com) | Test mode Key ID (`rzp_test_...`) |
| `RAZORPAY_KEY_SECRET` | ✅ | [Razorpay Dashboard](https://dashboard.razorpay.com) | Test mode Key Secret |
| `NEXT_PUBLIC_RAZORPAY_KEY_ID` | ✅ | Same as above | Client-exposed Key ID |
| `GOOGLE_CLIENT_ID` | ✅ | [Google Cloud Console](https://console.cloud.google.com/apis/credentials) | OAuth 2.0 Client ID |
| `GOOGLE_CLIENT_SECRET` | ✅ | [Google Cloud Console](https://console.cloud.google.com/apis/credentials) | OAuth 2.0 Client Secret |
| `SMTP_HOST` | ✅ | Gmail | `smtp.gmail.com` |
| `SMTP_PORT` | ✅ | Gmail | `587` |
| `SMTP_USER` | ✅ | Your Gmail | Email address for sending OTPs |
| `SMTP_PASSWORD` | ✅ | [Google App Passwords](https://myaccount.google.com/apppasswords) | 16-character app password |

---

## 📁 Project Structure

```
Railway-Management-System/
├── 📄 .env.example              # Environment variable template
├── 📄 .gitignore                # Git ignore rules
├── 📄 package.json              # Dependencies & scripts
├── 📄 tsconfig.json             # TypeScript configuration
├── 📄 next.config.ts            # Next.js configuration
├── 📄 postcss.config.mjs        # PostCSS + Tailwind
├── 📄 eslint.config.mjs         # ESLint configuration
│
├── 📁 prisma/
│   ├── 📄 schema.prisma         # Database schema (8 models, 4 enums)
│   ├── 📄 seed.ts               # Comprehensive seed script (285 trains)
│   └── 📁 migrations/           # SQL migration files
│
├── 📁 public/                   # Static assets
│
└── 📁 src/
    ├── 📁 app/                  # Next.js App Router
    │   ├── 📄 layout.tsx        # Root layout (fonts, providers, Razorpay script)
    │   ├── 📄 page.tsx          # Root redirect → /login
    │   ├── 📄 providers.tsx     # SessionProvider wrapper
    │   ├── 📄 globals.css       # Global styles + custom animations
    │   │
    │   ├── 📁 login/            # 3D login page
    │   ├── 📁 register/         # Registration page
    │   ├── 📁 forgot-password/  # OTP password reset (3-step)
    │   ├── 📁 dashboard/        # User dashboard
    │   ├── 📁 book-ticket/      # Multi-step booking wizard + 3D globe
    │   ├── 📁 payment/[bookingId]/ # Razorpay checkout + e-ticket
    │   ├── 📁 my-bookings/      # Booking history
    │   ├── 📁 pnr-enquiry/      # PNR status lookup
    │   ├── 📁 cancellation/     # Ticket cancellation
    │   ├── 📁 track-train/      # Live train tracking
    │   ├── 📁 admin/            # Admin dashboard
    │   │
    │   └── 📁 api/              # API route handlers
    │       ├── 📁 auth/         # Authentication endpoints
    │       ├── 📁 trains/       # Train search
    │       ├── 📁 bookings/     # Booking CRUD + admin
    │       ├── 📁 payments/     # Razorpay order + verify
    │       └── 📁 cancellations/ # Cancellation + PNR lookup
    │
    ├── 📁 components/
    │   ├── 📁 3d/               # Three.js scene components
    │   │   ├── 📄 LoginScene.tsx       # Dual-track train animation
    │   │   ├── 📄 RouteGlobe.tsx       # Globe → India map visualizer
    │   │   └── 📄 indiaStateBorders.ts # Real state boundary data
    │   │
    │   ├── 📁 ui/               # Design system components
    │   │   ├── 📄 button.tsx    # Primary/Secondary/Ghost/Danger variants
    │   │   ├── 📄 input.tsx     # Input with label, icon, error state
    │   │   ├── 📄 card.tsx      # Default/Glass/Elevated variants
    │   │   ├── 📄 select.tsx    # Styled select dropdown
    │   │   ├── 📄 badge.tsx     # Status badges (Success/Warning/Danger/Info)
    │   │   ├── 📄 modal.tsx     # Animated modal with backdrop
    │   │   ├── 📄 navbar.tsx    # Top navigation with mobile drawer
    │   │   └── 📄 skeleton.tsx  # Loading skeleton
    │   │
    │   └── 📁 booking/          # Booking-specific components
    │
    └── 📁 lib/                  # Shared utilities
        ├── 📄 auth.ts           # NextAuth configuration (Google + Credentials)
        ├── 📄 prisma.ts         # Prisma client singleton
        ├── 📄 validators.ts     # Zod schemas for all forms
        ├── 📄 encryption.ts     # AES-256 encrypt/decrypt/mask Aadhar
        ├── 📄 email.ts          # Nodemailer OTP email sender
        ├── 📄 pnr.ts            # PNR generation + refund calculation
        └── 📄 utils.ts          # cn() utility for class merging
```

---

## 🎨 Design System

### Color Palette

| Token | Hex | Usage |
|-------|-----|-------|
| Background | `#0B1120` | Page background, ground planes |
| Surface | `#1E293B` | Cards, panels |
| Border | `#334155` | Subtle borders |
| Text Primary | `#F8FAFC` | Headings, primary text |
| Text Secondary | `#94A3B8` | Body text, descriptions |
| Text Muted | `#64748B` | Hints, placeholders |
| Accent (Amber) | `#F59E0B` | CTAs, highlights, route lines |
| Accent Light | `#FBBF24` | Hover states, glows |
| Success | `#10B981` | Confirmed status |
| Warning | `#F59E0B` | Pending/waitlisted status |
| Danger | `#EF4444` | Cancelled, errors |

### Component Variants

| Component | Variants |
|-----------|----------|
| **Button** | `primary` (amber gradient), `secondary` (slate), `ghost` (transparent), `danger` (red) |
| **Card** | `default` (bordered), `glass` (backdrop-blur), `elevated` (shadow + hover) |
| **Badge** | `success`, `warning`, `danger`, `info`, `default` |
| **Input** | Standard with optional label, icon, error message |

---

## 🧪 Testing

### Razorpay Test Credentials

| Method | Details |
|--------|---------|
| **Test Card** | `4111 1111 1111 1111` (any future expiry, any CVV) |
| **Test UPI** | `success@razorpay` |
| **OTP (Razorpay)** | Any 6 digits |

### Manual Test Flow

1. Login with `demo@railone.in` / `password123`
2. Book a ticket: Mumbai → Delhi, select a class, fill passenger details
3. On payment page: click "Pay" → use test card → verify e-ticket shows
4. Check `/my-bookings` for the confirmed booking
5. Try PNR enquiry with the generated PNR number
6. Cancel the booking, verify refund calculation

---

## 📜 Available Scripts

| Command | Description |
|---------|-------------|
| `npm run dev` | Start development server (http://localhost:3000) |
| `npm run build` | Production build |
| `npm run start` | Start production server |
| `npm run lint` | Run ESLint |
| `npm run db:migrate` | Run Prisma migrations |
| `npm run db:seed` | Seed database with train data |
| `npm run db:reset` | Reset database and re-seed |

---

## 🌐 Deployment

This app is ready for deployment on:

| Platform | Notes |
|----------|-------|
| **Vercel** | Zero-config Next.js deployment (recommended) |
| **Railway** | Add PostgreSQL addon, set `DATABASE_URL` |
| **Render** | Use Web Service + PostgreSQL |
| **AWS** | EC2/ECS with RDS PostgreSQL |

For production, change `DATABASE_URL` from SQLite to PostgreSQL and update `prisma/schema.prisma` provider to `"postgresql"`.

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  <strong>Built with ❤️ by <a href="https://github.com/AkulRaghav">Akul Raghav</a></strong>
</p>

<p align="center">
  <em>From a 2020s Java Swing desktop app to a 2026 production-grade web platform.</em>
</p>
