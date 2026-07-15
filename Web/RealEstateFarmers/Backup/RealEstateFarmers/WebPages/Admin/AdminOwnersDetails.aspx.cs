using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;

namespace RealEstateFarmers.WebPages.Admin
{
    public partial class AdminOwnersDetails : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                lblID.Text = Request.QueryString["ID"].ToString();

                fillDetails();
            }
        }

        private void fillDetails()
        {
            string sql = "select * from tblOwners where ID='" + lblID.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblID.Text = dr[0].ToString().Trim();
                lblOwnerName.Text = dr[1].ToString().Trim();
                lblAddressLine1.Text = dr[2].ToString().Trim();
                lblAddressLine2.Text = dr[3].ToString().Trim();
                lblCity.Text = dr[4].ToString().Trim();
                lblState.Text = dr[5].ToString().Trim();
                lblMobile.Text = dr[6].ToString().Trim();
                lblEmailID.Text = dr[7].ToString().Trim();
            }
            dr.Close();
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            Response.Redirect("AdminOwnersList.aspx");
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            if (lblID.Text.Trim() == "")
                readyclass.errormessage(lblerror, "Select Owner to delete");
            else
            {
                string sql = "delete from tblOwners where ID = '" + lblID.Text.Trim() + "'";
                Database.executeQuery(sql);

                sql = "delete from tblLogin where UserID = '" + lblMobile.Text.Trim() + "'";
                Database.executeQuery(sql);

                obj.Show("Deleted Successfully", "AdminOwnersList.aspx");
            }
        }

    }
}