<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminOwnersList.aspx.cs" Inherits="RealEstateFarmers.WebPages.Admin.AdminOwnersList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



<h1>Owners List</h1>

  

          <asp:Label ID="lblerror" runat="server" Text="" CssClass="error"></asp:Label> 
 
        <asp:GridView ID="grdOwners" runat="server" 
                     AutoGenerateColumns="False" Caption="Owners List" 
        CssClass="fulltable">
             <Columns>

                  <asp:BoundField DataField="OwnerName" HeaderText="Name">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="City" HeaderText="City">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                 <asp:BoundField DataField="Mobile" HeaderText="Mobile">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
               
        
               <asp:HyperLinkField DataNavigateUrlFields="ID" DataNavigateUrlFormatString="AdminOwnersDetails.aspx?ID={0}" Text="Info" HeaderText="Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
</asp:GridView>

</asp:Content>
