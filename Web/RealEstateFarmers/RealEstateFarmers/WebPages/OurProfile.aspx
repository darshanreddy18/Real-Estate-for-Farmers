<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/User.Master" AutoEventWireup="true" CodeBehind="OurProfile.aspx.cs" Inherits="RealEstateFarmers.WebPages.OurProfile" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div class="dashboard-card" style="margin-top: 40px; border-top: 6px solid var(--primary-color);">
    <h2 style="color: var(--primary-color); margin-bottom: 30px; font-weight: 700; font-size: 2rem;">
        <i class="fas fa-eye" style="margin-right: 15px; color: var(--accent-color);"></i> Our Vision & Mission
    </h2>
    <div style="font-size: 1.2rem; line-height: 1.9; color: var(--text-main); border-left: 4px solid var(--accent-color); padding-left: 30px; margin-bottom: 50px; font-style: italic; background: var(--bg-light); padding-top: 20px; padding-bottom: 20px; border-radius: 0 12px 12px 0;">
        Farms.com’s vision is to be the essential partner to our global agriculture and food industry customers; by exceeding their expectations through value-added & integrated information-driven services and solutions enabled by technology.
    </div>

    <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 50px; margin-top: 40px;">
        <div style="background: var(--bg-light); padding: 30px; border-radius: 16px;">
            <h4 style="color: var(--primary-color); margin-bottom: 15px; text-transform: uppercase; font-size: 1rem; letter-spacing: 1.5px; font-weight: 700;">Global Reach</h4>
            <p style="color: var(--text-muted); font-size: 1rem; line-height: 1.7;">
                Farms.com Ltd. is a leading provider of innovative information products and services for the global agriculture and food industries. 
                Our portal serves over 25,000 agribusiness professionals daily.
            </p>
        </div>
        <div style="background: var(--bg-light); padding: 30px; border-radius: 16px;">
            <h4 style="color: var(--primary-color); margin-bottom: 15px; text-transform: uppercase; font-size: 1rem; letter-spacing: 1.5px; font-weight: 700;">Strategic Network</h4>
            <p style="color: var(--text-muted); font-size: 1rem; line-height: 1.7;">
                Our family includes AgCareers.com, PigCHAMP, and Farms.com Risk Management, 
                providing end-to-end expertise across the entire agricultural value chain.
            </p>
        </div>
    </div>
</div>

</asp:Content>
