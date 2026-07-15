<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/User.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="RealEstateFarmers.WebPages.Index" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .hero-section {
            padding: 120px 0;
            text-align: center;
            background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
            border-bottom: 1px solid var(--border-color);
            margin-bottom: 60px;
            border-radius: 30px;
            box-shadow: 0 20px 40px -10px rgba(6, 95, 70, 0.2);
            color: white;
        }
        .hero-title {
            font-size: 4rem;
            font-weight: 800;
            color: white;
            margin-bottom: 20px;
            letter-spacing: -1.5px;
        }
        .hero-subtitle {
            font-size: 1rem;
            color: #fbbf24; /* Brighter Amber */
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 6px;
            margin-bottom: 30px;
            background: rgba(255, 255, 255, 0.1);
            display: inline-block;
            padding: 10px 25px;
            border-radius: 50px;
            backdrop-filter: blur(5px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        .feature-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 40px;
            margin-top: 60px;
        }
        .feature-card {
            background: var(--surface);
            padding: 50px 40px;
            border-radius: 24px;
            border: 1px solid var(--border-color);
            transition: var(--transition);
            text-align: center;
            box-shadow: var(--card-shadow);
        }
        .feature-card:hover {
            border-color: var(--primary-color);
            transform: translateY(-10px);
            box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.08);
        }
        .feature-icon {
            font-size: 3.5rem;
            color: var(--primary-color);
            margin-bottom: 25px;
            display: inline-block;
            transition: var(--transition);
        }
        .feature-card:hover .feature-icon {
            color: var(--accent-color);
            transform: scale(1.1);
        }
        .feature-card h3 {
            font-size: 1.5rem;
            color: var(--text-main);
            margin-bottom: 15px;
            font-weight: 700;
        }
        .feature-card p {
            color: var(--text-muted);
            font-size: 1rem;
            line-height: 1.7;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <div class="hero-section">
        <div class="content" style="margin: 0 auto !important;">
            <p class="hero-subtitle">Agricultural Excellence</p>
            <h1 class="hero-title">Premier Farm Estates</h1>
            <p style="color: rgba(255, 255, 255, 0.9); max-width: 850px; margin: 0 auto 45px; font-size: 1.2rem; line-height: 1.8; font-weight: 400;">
                Experience the pinnacle of farm property management. Whether you're investing, 
                cultivating, or expanding, our platform provides the elite tools you need to succeed.
            </p>
            <div style="display: flex; gap: 20px; justify-content: center; align-items: center;">
                <a href="Login.aspx" class="asp-button" style="text-decoration: none; padding: 18px 45px !important; font-size: 1rem; box-shadow: 0 10px 20px rgba(0,0,0,0.2);">EXPLORE ASSETS</a>
                <a href="OurProfile.aspx" class="asp-button" style="text-decoration: none; background: transparent !important; border: 2px solid rgba(255, 255, 255, 0.4) !important; color: white !important; padding: 16px 45px !important; font-size: 1rem; backdrop-filter: blur(5px);">OUR VISION</a>
            </div>
        </div>
    </div>

    <div class="dashboard-card" style="margin-bottom: 60px;">
        <h2 style="color: var(--primary-color); margin-bottom: 25px; font-size: 2rem; font-weight: 700;">
            <i class="fas fa-landmark" style="margin-right: 15px; color: var(--accent-color);"></i> Karnataka's Trusted Land Partner
        </h2>
        <p style="font-size: 1.1rem; line-height: 1.9; color: var(--text-main);">
            Farms.com Real Estate is the region's leading authority in agricultural land transactions. 
            We specialize in connecting visionaries with fertile grounds, ensuring every acquisition 
            is backed by data, security, and agricultural expertise.
        </p>
        <div style="margin-top: 40px; padding-top: 30px; border-top: 1px solid var(--border-color); display: flex; align-items: center; gap: 20px;">
            <i class="fas fa-envelope-open-text" style="color: var(--primary-color); font-size: 1.2rem;"></i>
            <span style="color: var(--text-muted); font-weight: 500;">Inquiries: <a href="mailto:realestate@farms.com" style="color: var(--primary-color); text-decoration: none; font-weight: 700; border-bottom: 2px solid var(--primary-light);">realestate@farms.com</a></span>
        </div>
    </div>

    <div class="feature-grid">
        <div class="feature-card">
            <i class="fas fa-microscope feature-icon"></i>
            <h3>Soil Analytics</h3>
            <p>Advanced data on soil health and crop suitability for informed investment decisions.</p>
        </div>
        <div class="feature-card">
            <i class="fas fa-shield-virus feature-icon"></i>
            <h3>Verified Titles</h3>
            <p>Every listing undergoes a rigorous legal verification process for your peace of mind.</p>
        </div>
        <div class="feature-card">
            <i class="fas fa-hand-holding-heart feature-icon"></i>
            <h3>Farmer Support</h3>
            <p>End-to-end guidance from property selection to agricultural operational setup.</p>
        </div>
    </div>

</asp:Content>
