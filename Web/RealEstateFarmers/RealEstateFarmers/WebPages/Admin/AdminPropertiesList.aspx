<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminPropertiesList.aspx.cs" Inherits="RealEstateFarmers.WebPages.Admin.AdminPropertiesList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



<div class="dashboard-card" style="margin-bottom: 20px;">
    <h3 style="color: var(--accent-color); margin-bottom: 15px;"><i class="fas fa-filter"></i> Filter by Category</h3>
    <table class="minitable" style="margin: 0 !important; border: none !important; background: transparent !important;">
        <tr>
            <td style="border: none !important; width: 150px;">Select Category</td>
            <td style="border: none !important;">
                <asp:DropDownList ID="ddlCategory" runat="server" AutoPostBack="true" 
                    onselectedindexchanged="ddlCategory_SelectedIndexChanged">
                </asp:DropDownList>
            </td>
        </tr>
    </table>
</div>

          <asp:Label ID="lblerror" runat="server" Text="" CssClass="error"></asp:Label> 
 
        <asp:GridView ID="grdProperties" runat="server" 
                     AutoGenerateColumns="False" Caption="Properties List" 
        CssClass="fulltable">
             <Columns>

                  <asp:BoundField DataField="Category" HeaderText="Category">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="City" HeaderText="City">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                 <asp:BoundField DataField="OwnerID" HeaderText="Owner Mobile">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
               
        
               <asp:HyperLinkField DataNavigateUrlFields="ID" DataNavigateUrlFormatString="AdminProeprtiesDetails.aspx?ID={0}" Text="Info" HeaderText="Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
</asp:GridView>

</asp:Content>
