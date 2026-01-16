# Booki - Restaurant Management System

![Language](https://img.shields.io/badge/Language-Java-orange)
![Platform](https://img.shields.io/badge/Platform-Android_Native-green)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue)

[cite_start]**Booki** (COMP2000 Restaurant Application) is a native Android solution designed to streamline operational workflows and enhance customer engagement[cite: 4]. [cite_start]The application features a unified mobile interface with distinct, role-based experiences for **Staff** and **Guests**[cite: 9].

## 📖 Overview

[cite_start]The application addresses the need for efficient mobile management by offering a dual-interface system[cite: 5]. It implements a "Hybrid Data Approach" to ensure reliability:
* [cite_start]**Staff Operations** function **offline-first** using a local SQLite database, ensuring critical business tasks (menu updates, reservation tracking) continue during network outages[cite: 231].
* [cite_start]**Guest Operations** rely on a **remote REST API** for centralized user authentication and profile management, allowing scalability across locations[cite: 223].

## 🚀 Key Features

### 👨‍🍳 Staff Portal (Admin)
* [cite_start]**Hub-and-Spoke Dashboard:** A centralized hub to manage menus, reservations, and settings[cite: 172].
* **Menu Management:** Add, edit, or delete menu items. [cite_start]Changes are persisted locally via SQLite[cite: 200, 203].
* [cite_start]**Reservation Oversight:** View active bookings and receive real-time Android Notifications when new reservations are made[cite: 212].
* [cite_start]**Offline Reliability:** Local authentication and data storage ensure staff are never locked out of the system due to internet failure[cite: 192].

### 🍽️ Guest Portal (Customer)
* [cite_start]**Remote Authentication:** Secure login via REST API (Volley) to fetch user profiles from a remote server[cite: 223].
* [cite_start]**Browsing & Booking:** Browse the full menu and place reservation requests[cite: 10].
* [cite_start]**Profile Management:** Update user details, which are synchronized to the remote database[cite: 226].

## 🛠️ Tech Stack

* **Language:** Java
* [cite_start]**UI:** XML, Material Design Components (Buttons, Navigation Views) [cite: 5, 42]
* [cite_start]**Architecture:** MVVM (Model-View-ViewModel) & SOLID Principles [cite: 286]
* [cite_start]**Networking:** [Volley Library](https://google.github.io/volley/) for HTTP GET/PUT requests[cite: 224].
* [cite_start]**Local Database:** SQLite for persistent storage of Menus and Reservations[cite: 6].
* [cite_start]**Navigation:** Android Navigation Component (Single Activity, Multiple Fragments)[cite: 135].

## 🏗️ Architecture & Navigation

[cite_start]The app uses a single-activity architecture with a comprehensive **Navigation Graph** divided into three logical flows[cite: 102]:

1.  **Authentication Layer:** `LoginFragment` acts as the boundary.
    * [cite_start]*Staff* -> Authenticated via `DatabaseHelper` (Local) -> `StaffDashboardFragment`[cite: 191].
    * [cite_start]*Guests* -> Authenticated via `Api.loginUser` (Remote) -> `MenuFragment`[cite: 194, 227].
2.  [cite_start]**Staff Flow:** Centralized "Hub" pattern branching to Menu, Reservations, and Settings[cite: 172].
3.  [cite_start]**Guest Flow:** Bottom Navigation View providing access to Menu, Reservations, and Account Settings[cite: 141].

### Database Schema (SQLite)
[cite_start]The local database initializes automatically with default data to ensure immediate functionality[cite: 240].
* [cite_start]**`menu_items`**: Stores name, description, price, and image resources[cite: 235].
* **`reservations`**: Tracks guest counts, dates, times, and table numbers[cite: 236].
* [cite_start]**`users`**: Stores local staff credentials (role-based access)[cite: 238].

## 🔧 Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Plymouth-COMP2000/design-exercises-JorjitDasoria](https://github.com/Plymouth-COMP2000/design-exercises-JorjitDasoria)
    ```
2.  **Open in Android Studio:**
    * Select `File > Open` and navigate to the cloned directory.
    * Allow Gradle to sync dependencies (including Volley).
3.  **Run the App:**
    * Connect an Android device or start an Emulator.
    * Click the **Run** (green arrow) button.

## 🧪 Testing Accounts

[cite_start]To test the specific workflows, use the following credentials included in the database seeder[cite: 241]:

| Role | Username | Password | Storage |
| :--- | :--- | :--- | :--- |
| **Staff** | `123456` | `password` | Local SQLite |
| **Guest** | *(Requires valid API ID)* | *(Remote Check)* | Remote Server |
