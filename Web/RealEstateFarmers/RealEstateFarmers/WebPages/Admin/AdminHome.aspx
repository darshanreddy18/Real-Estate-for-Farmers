<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminHome.aspx.cs" Inherits="RealEstateFarmers.WebPages.Admin.AdminHome" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .premium-hero {
            position: relative;
            background: url('../../images/dashboard_hero.png') center/cover no-repeat;
            border-radius: 20px;
            padding: 60px 50px; /* Increased padding */
            margin-bottom: 25px;
            min-height: 300px; /* Ensure enough height */
            display: flex;
            align-items: center;
            overflow: hidden;
            box-shadow: 0 15px 35px rgba(0,0,0,0.2);
        }
        .premium-hero::before {
            content: '';
            position: absolute;
            inset: 0;
            background: linear-gradient(135deg, rgba(8, 28, 21, 0.95) 0%, rgba(8, 28, 21, 0.7) 100%); /* Darker, more consistent overlay */
        }
        .hero-content {
            position: relative;
            z-index: 2;
        }
        .stat-card {
            background: white;
            border-radius: 15px; /* More compact */
            padding: 20px; /* Reduced padding */
            display: flex;
            flex-direction: column;
            gap: 12px;
            transition: var(--transition);
            border: 1px solid var(--border-color);
        }
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px -10px rgba(0,0,0,0.1);
        }
        .icon-box {
            width: 45px; /* Smaller icon boxes */
            height: 45px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.1rem;
        }
        .stat-value {
            font-size: 1.6rem !important; /* Smaller values */
            font-weight: 800;
            color: var(--text-main);
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <!-- Premium Hero Section (Compact) -->
    <div class="premium-hero">
        <div class="hero-content">
            <div style="display: inline-block; padding: 5px 15px; background: var(--accent-color); border-radius: 50px; color: var(--bg-dark); font-weight: 800; font-size: 0.65rem; letter-spacing: 2px; margin-bottom: 15px; text-transform: uppercase;">
                System Online
            </div>
            <h1 style="color: white; font-size: 2.2rem; font-weight: 800; margin: 0 0 10px 0; line-height: 1.2;">
                Admin <span class="gradient-text">Command Center</span>
            </h1>
            <p style="color: rgba(255,255,255,0.7); font-size: 0.95rem; max-width: 500px; line-height: 1.6; margin: 0;">
                High-performance oversight for the agricultural real estate ecosystem.
            </p>
        </div>
    </div>

    <!-- Stats Section (Compact) -->
    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 20px; margin-bottom: 30px;">
        <div class="stat-card">
            <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                <div class="icon-box" style="background: #fff7ed; color: #ea580c;">
                    <i class="fas fa-layer-group"></i>
                </div>
                <div style="color: #10b981; font-weight: 700; font-size: 0.75rem;">+12%</div>
            </div>
            <div>
                <h4 style="color: var(--text-muted); font-size: 0.7rem; font-weight: 600; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 2px;">Categories</h4>
                <div class="stat-value">Field-A</div>
            </div>
        </div>

        <div class="stat-card">
            <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                <div class="icon-box" style="background: #f0fdf4; color: #16a34a;">
                    <i class="fas fa-user-friends"></i>
                </div>
                <div style="color: #10b981; font-weight: 700; font-size: 0.75rem;">+25%</div>
            </div>
            <div>
                <h4 style="color: var(--text-muted); font-size: 0.7rem; font-weight: 600; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 2px;">Users</h4>
                <div class="stat-value">Active</div>
            </div>
        </div>

        <div class="stat-card">
            <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                <div class="icon-box" style="background: #eff6ff; color: #2563eb;">
                    <i class="fas fa-briefcase"></i>
                </div>
                <div style="color: #3b82f6; font-weight: 700; font-size: 0.75rem;">Stable</div>
            </div>
            <div>
                <h4 style="color: var(--text-muted); font-size: 0.7rem; font-weight: 600; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 2px;">Owners</h4>
                <div class="stat-value">Partners</div>
            </div>
        </div>

        <div class="stat-card">
            <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                <div class="icon-box" style="background: #faf5ff; color: #9333ea;">
                    <i class="fas fa-map-marked-alt"></i>
                </div>
                <div style="color: #10b981; font-weight: 700; font-size: 0.75rem;">+8%</div>
            </div>
            <div>
                <h4 style="color: var(--text-muted); font-size: 0.7rem; font-weight: 600; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 2px;">Properties</h4>
                <div class="stat-value">Acres</div>
            </div>
        </div>
    </div>

    <!-- Compact Actions -->
    <div style="display: grid; grid-template-columns: 1.5fr 1fr; gap: 20px;">
        <div class="glass" style="padding: 25px; border-radius: 20px;">
            <h3 style="margin-bottom: 15px; font-weight: 800; color: var(--text-main); font-size: 1.1rem;">Guidelines</h3>
            <div style="display: flex; flex-direction: column; gap: 12px;">
                <div style="display: flex; gap: 15px; align-items: center; padding: 12px; background: rgba(255,255,255,0.6); border-radius: 12px;">
                    <div style="width: 8px; height: 8px; background: var(--accent-color); border-radius: 50%;"></div>
                    <p style="margin: 0; color: var(--text-main); font-weight: 600; font-size: 0.85rem;">Full data oversight enabled.</p>
                </div>
                <div style="display: flex; gap: 15px; align-items: center; padding: 12px; background: rgba(255,255,255,0.6); border-radius: 12px;">
                    <div style="width: 8px; height: 8px; background: var(--primary-color); border-radius: 50%;"></div>
                    <p style="margin: 0; color: var(--text-main); font-weight: 600; font-size: 0.85rem;">Secure encryption active.</p>
                </div>
            </div>
        </div>
        
        <div class="glass-dark" style="padding: 25px; border-radius: 20px; color: white;">
            <h3 style="margin-bottom: 15px; font-weight: 800; color: var(--accent-color); font-size: 1.1rem;">Status</h3>
            <div style="display: flex; flex-direction: column; gap: 10px; font-size: 0.85rem;">
                <div style="display: flex; justify-content: space-between;">
                    <span style="opacity: 0.6;">Uptime</span>
                    <span style="font-weight: 700;">99.9%</span>
                </div>
                <div style="display: flex; justify-content: space-between;">
                    <span style="opacity: 0.6;">Load</span>
                    <span style="font-weight: 700; color: #10b981;">Low</span>
                </div>
                <div style="display: flex; justify-content: space-between;">
                    <span style="opacity: 0.6;">Active</span>
                    <span style="font-weight: 700;">1.2k</span>
                </div>
            </div>
        </div>
    </div>

</asp:Content>
