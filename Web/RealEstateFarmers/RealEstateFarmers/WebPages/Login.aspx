<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/User.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="RealEstateFarmers.WebPages.Login" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .login-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
            padding: 40px;
        }
        .login-card {
            background: var(--surface);
            border: 1px solid var(--border-color);
            border-radius: 24px;
            padding: 60px;
            width: 100%;
            max-width: 500px;
            box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.08);
            text-align: center;
            transition: var(--transition);
        }
        .login-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 35px 60px -15px rgba(0, 0, 0, 0.12);
        }
        .login-header {
            margin-bottom: 45px;
        }
        .login-header i {
            font-size: 4.5rem;
            background: linear-gradient(135deg, var(--primary-color), var(--accent-color));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            margin-bottom: 25px;
        }
        .login-header h1 {
            font-size: 2.5rem;
            font-weight: 800;
            margin: 0;
            color: var(--text-main);
            letter-spacing: -0.5px;
        }
        .input-group {
            text-align: left;
            margin-bottom: 30px;
        }
        .input-group label {
            display: block;
            margin-bottom: 12px;
            font-size: 0.9rem;
            color: var(--text-main);
            font-weight: 600;
            letter-spacing: 0.3px;
        }
        .btn-login {
            width: 100%;
            margin-top: 25px;
            padding: 18px !important;
            font-size: 1.1rem !important;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-weight: 700 !important;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <div class="login-wrapper">
        <div class="login-card">
            <div class="login-header">
                <i class="fas fa-user-circle"></i>
                <h1>Login</h1>
                <p style="color: var(--text-muted); margin-top: 8px; font-size: 0.9rem;">Sign in to your farm account</p>
            </div>

            <div class="input-group">
                <label>User ID</label>
                <asp:TextBox ID="txtUserID" runat="server" MaxLength="10" placeholder="Enter User ID"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Enter User Id" ControlToValidate="txtUserID" Display="Dynamic" ValidationGroup="test" CssClass="error" style="font-size: 0.75rem; color: #f44336;"></asp:RequiredFieldValidator>
            </div>

            <div class="input-group">
                <label>Password</label>
                <asp:TextBox ID="txtPassword" runat="server" MaxLength="12" TextMode="Password" placeholder="Enter Password"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Enter Password" ControlToValidate="txtPassword" Display="Dynamic" ValidationGroup="test" CssClass="error" style="font-size: 0.75rem; color: #f44336;"></asp:RequiredFieldValidator>
            </div>

            <asp:Button ID="btnLogin" runat="server" Text="Sign In"  
                    ValidationGroup="test" onclick="btnLogin_Click" CssClass="btn-login" />

            <div style="margin-top: 25px;">
                <asp:Label ID="lblerror" runat="server" Text="---" Visible="false" CssClass="error" style="font-size: 0.85rem; color: #dc2626; font-weight: 500;"></asp:Label>
            </div>

            <div style="margin-top: 40px; font-size: 0.9rem; color: var(--text-muted); border-top: 1px solid var(--border-color); padding-top: 30px;">
                Restricted access. Need help? <a href="#" style="color: var(--primary-color); text-decoration: none; font-weight: 600;">Contact Support</a>
            </div>
        </div>
    </div>

</asp:Content>
