<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminBookedProeprtiesDetails.aspx.cs" Inherits="RealEstateFarmers.WebPages.Admin.AdminBookedProeprtiesDetails" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



<h1>Property Details</h1>

<table class="fulltable">
<tr>
<th colspan="4" style="text-align:center;">
Property Details
</th>
</tr>

<tr>
<td>
Property ID
</td>
<td>
    <asp:Label ID="lblID" runat="server" Text="Label"></asp:Label>
</td>

<td>
Category
</td>
<td>
    <asp:Label ID="lblCategory" runat="server" Text="Label"></asp:Label>
</td>

</tr>


<tr>
<td>
Address Line1
</td>
<td>
    <asp:Label ID="lblAddressLine1" runat="server" Text="Label"></asp:Label>
</td>

<td>
Address Line2
</td>
<td>
    <asp:Label ID="lblAddressLine2" runat="server" Text="Label"></asp:Label>
</td>

</tr>



<tr>
<td>
City
</td>
<td>
    <asp:Label ID="lblCity" runat="server" Text="Label"></asp:Label>
</td>

<td>
State
</td>
<td>
    <asp:Label ID="lblState" runat="server" Text="Label"></asp:Label>
</td>

</tr>



<tr>
<td>
Area
</td>
<td colspan="3">
    <asp:Label ID="lblArea" runat="server" Text="Label"></asp:Label>
</td>
</tr>

<tr>
<td>
Description
</td>
<td colspan="3">
    <asp:Label ID="Label2" runat="server" Text="Label"></asp:Label>
</td>

</tr>

<tr>
<td>
Features
</td>
<td colspan="3">
    <asp:Label ID="lblFeatures" runat="server" Text="Label"></asp:Label>
</td>

</tr>


<tr>
<td>
Remarks
</td>
<td colspan="3">
    <asp:Label ID="lblRemarks" runat="server" Text="Label"></asp:Label>
</td>

</tr>


<tr>
<td>
Type
</td>
<td>
    <asp:Label ID="lblType" runat="server" Text="Label"></asp:Label>
</td>

<td>
Price
</td>
<td>
    <asp:Label ID="lblPrice" runat="server" Text="Label"></asp:Label>
</td>

</tr>




<tr>
<td>
Status
</td>
<td>
    <asp:Label ID="lblStatus" runat="server" Text="Label"></asp:Label>
</td>

<td colspan="2">
    <asp:Button ID="btnDelete" runat="server" Text="Delete" 
        onclick="btnDelete_Click" />
    <asp:Button ID="btnBack" runat="server" Text="Back" onclick="btnBack_Click" />
</td>


</tr>


<tr>
<th colspan="4" style="text-align:center;">
Owner Details
</th>
</tr>



<tr>
                <td>
                  Owner Name
                </td>
                <td colspan="3">
                    <asp:Label ID="lblOwnerName" runat="server" Text=""></asp:Label>
                </td>
                </tr>

                <tr>
                <td >
                 AddressLine1
                </td>
                <td>
                <asp:Label ID="lblOAddressLine1" runat="server" Text=""></asp:Label>
                </td>
          
                <td >
                AddressLine2
                </td>
                <td>
                  <asp:Label ID="lblOAddressLine2" runat="server" Text=""></asp:Label>
                </td>
            </tr>

              <tr>
       <td>
        City
       </td>
       <td>
          <asp:Label ID="lblOCity" runat="server" Text=""></asp:Label>    
             </td>
               
       <td>
     State
       </td>
       <td>
         <asp:Label ID="lblOState" runat="server" Text=""></asp:Label>    
           </td>
</tr>

           

                <tr>
                <td >
                Mobile
                </td>
                <td>
                   <asp:Label ID="lblOMobile" runat="server" Text=""></asp:Label>        
                           </td>
         
                <td >
                Email ID
                </td>
                <td>
                   <asp:Label ID="lblOEmailID" runat="server" Text=""></asp:Label>        
                           </td>
            </tr>





            <tr>
<th colspan="4">
User Details
</th>
</tr>


<tr>
                <td>
                  User Name
                </td>
                <td colspan="3">
                    <asp:Label ID="lblUserName" runat="server" Text=""></asp:Label>
                </td>
                </tr>

                <tr>
                <td >
                 AddressLine1
                </td>
                <td>
                <asp:Label ID="lblUAddressLine1" runat="server" Text=""></asp:Label>
                </td>
          
                <td >
                AddressLine2
                </td>
                <td>
                  <asp:Label ID="lblUAddressLine2" runat="server" Text=""></asp:Label>
                </td>
            </tr>

              <tr>
       <td>
        City
       </td>
       <td>
          <asp:Label ID="lblUCity" runat="server" Text=""></asp:Label>    
             </td>
            
       <td>
     State
       </td>
       <td>
         <asp:Label ID="lblUState" runat="server" Text=""></asp:Label>    
           </td>
</tr>

           

                <tr>
                <td >
                Mobile
                </td>
                <td>
                   <asp:Label ID="lblUMobile" runat="server" Text=""></asp:Label>        
                           </td>
         
                <td >
                Email ID
                </td>
                <td>
                   <asp:Label ID="lblUEmailID" runat="server" Text=""></asp:Label>        
                           </td>
            </tr>

            
<tr>
<th colspan="4" style="text-align:center;">
Images
</th>
</tr>

<tr>
<td colspan="4">

 <asp:GridView ID="grdImages" runat="server" 
                     AutoGenerateColumns="False" Caption="Images List" 
        CssClass="fulltable" onrowdatabound="grdImages_RowDataBound" 
        onrowdeleting="grdImages_RowDeleting"  DataKeyNames="ID">
             <Columns>

                  <asp:BoundField DataField="Subject" HeaderText="Subject">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="Description" HeaderText="Description">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
             
                <asp:TemplateField HeaderText="Image">
        <ItemTemplate>
            <asp:ImageButton ID="ImageButton1" runat="server" ImageUrl='<%# Eval("Image")%>'
                CssClass = "miniimg" Style="cursor: pointer" OnClientClick="return LoadDiv(this.src);" />
        </ItemTemplate>
    </asp:TemplateField>

     <asp:CommandField ShowDeleteButton="True" />  
            </Columns>
</asp:GridView>



</td>

</tr>



<tr>
<th colspan="4" style="text-align:center;">
Feed Back
</th>
</tr>

<tr>
<td colspan="4">

 <asp:GridView ID="grdFeedBack" runat="server" 
                     AutoGenerateColumns="False" Caption="FeedBack List" 
        CssClass="fulltable"  DataKeyNames="ID" 
        onrowdatabound="grdFeedBack_RowDataBound" 
        onrowdeleting="grdFeedBack_RowDeleting">
             <Columns>

                  <asp:BoundField DataField="UserName" HeaderText="UserName">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="FeedBack" HeaderText="FeedBack">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                 <asp:CommandField ShowDeleteButton="True" />  
            </Columns>
</asp:GridView>



</td>

</tr>


</table>

<asp:Label ID="lblerror" runat="server" Text=""></asp:Label>

</asp:Content>
