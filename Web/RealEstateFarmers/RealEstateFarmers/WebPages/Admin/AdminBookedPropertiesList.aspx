<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminBookedPropertiesList.aspx.cs" Inherits="RealEstateFarmers.WebPages.Admin.AdminBookedPropertiesList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">




<h1>Properties List</h1>

  <table class="minitable">
  <tr>
  <td>Status</td>
  <td>
      <asp:DropDownList ID="ddlStatus" runat="server" AutoPostBack="true" 
          onselectedindexchanged="ddlStatus_SelectedIndexChanged">
      </asp:DropDownList>
  </td>
  </tr>
  
  </table>

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
               
        
               <asp:HyperLinkField DataNavigateUrlFields="ID" DataNavigateUrlFormatString="AdminBookedProeprtiesDetails.aspx?ID={0}" Text="Info" HeaderText="Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
</asp:GridView>


</asp:Content>
