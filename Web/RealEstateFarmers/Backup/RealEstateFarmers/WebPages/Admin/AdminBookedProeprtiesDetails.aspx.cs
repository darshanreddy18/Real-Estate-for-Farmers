using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;
using System.Data;
namespace RealEstateFarmers.WebPages.Admin
{
    public partial class AdminBookedProeprtiesDetails : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                lblID.Text = Request.QueryString["ID"].ToString();

                fillPropertyDetails();

                fillOwnerDetails();

                fillFeedBackList();


                fillImageList();

                fillUserDetails();
            }
        }

        private void fillPropertyDetails()
        {
            string sql = "Select * from tblProperties where ID='" + lblID.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblCategory.Text = dr["Category"].ToString();
                lblAddressLine1.Text = dr["AddressLine1"].ToString();
                lblAddressLine2.Text = dr["AddressLine2"].ToString();
                lblCity.Text = dr["City"].ToString();
                lblState.Text = dr["State"].ToString();
                lblArea.Text = dr["Area"].ToString();
                lblFeatures.Text = dr["Features"].ToString();
                lblRemarks.Text = dr["Remarks"].ToString();
                lblType.Text = dr["Type"].ToString();
                lblPrice.Text = dr["Price"].ToString();
                lblStatus.Text = dr["Status"].ToString();
                lblOMobile.Text = dr["OwnerID"].ToString();
                lblUMobile.Text = dr["UserID"].ToString();
            }
        }

        private void fillOwnerDetails()
        {
            string sql = "select * from tblOwners where Mobile='" + lblOMobile.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblOwnerName.Text = dr[1].ToString().Trim();
                lblOAddressLine1.Text = dr[2].ToString().Trim();
                lblOAddressLine2.Text = dr[3].ToString().Trim();
                lblOCity.Text = dr[4].ToString().Trim();
                lblOState.Text = dr[5].ToString().Trim();
                lblOMobile.Text = dr[6].ToString().Trim();
                lblOEmailID.Text = dr[7].ToString().Trim();
            }
            dr.Close();
        }


        private void fillUserDetails()
        {
            string sql = "select * from tblUsers where Mobile='" + lblUMobile.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblUserName.Text = dr[1].ToString().Trim();
                lblUAddressLine1.Text = dr[2].ToString().Trim();
                lblUAddressLine2.Text = dr[3].ToString().Trim();
                lblUCity.Text = dr[4].ToString().Trim();
                lblUState.Text = dr[5].ToString().Trim();
                lblUMobile.Text = dr[6].ToString().Trim();
                lblUEmailID.Text = dr[7].ToString().Trim();
            }
            dr.Close();
        }



        private void fillFeedBackList()
        {
            string sql = "select * from tblFeedBack where PropertyID='" + lblID.Text.Trim() + "'";
            obj.fill(grdFeedBack, sql, lblerror);
        }

        private void fillImageList()
        {
            string sql = "select * from tblPropertyImages where PropertyID='" + lblID.Text.Trim() + "'";
            obj.fill(grdImages, sql, lblerror);
        }

        protected void grdImages_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                DataRowView dr = (DataRowView)e.Row.DataItem;
                byte[] imgbyte = Convert.FromBase64String(dr["Image"].ToString());

                string imageUrl = "data:image/jpg;base64," + Convert.ToBase64String(imgbyte);
                (e.Row.FindControl("ImageButton1") as Image).ImageUrl = imageUrl;
            }

            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // reference the Delete LinkButton
                LinkButton db = (LinkButton)e.Row.Cells[3].Controls[0];

                db.OnClientClick = "return confirm('Are you certain you want to delete this?');";
            }
        }

        protected void grdImages_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            string release;
            release = grdImages.DataKeys[e.RowIndex].Values[0].ToString();

            string sql = "delete from tblPropertyImages where ID = " + release + "";
            Database.executeQuery(sql);

            fillImageList();
        }

        protected void grdFeedBack_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            string release;
            release = grdFeedBack.DataKeys[e.RowIndex].Values[0].ToString();

            string sql = "delete from tblFeedBack where ID = " + release + "";
            Database.executeQuery(sql);

            fillFeedBackList();
        }

        protected void grdFeedBack_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // reference the Delete LinkButton
                LinkButton db = (LinkButton)e.Row.Cells[2].Controls[0];

                db.OnClientClick = "return confirm('Are you certain you want to delete this?');";
            }
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            string sql = "delete from tblProperties where ID = '" + lblID.Text.Trim() + "'";
            Database.executeQuery(sql);

            sql = "delete from tblFeedBack where PropertyID = '" + lblID.Text.Trim() + "'";
            Database.executeQuery(sql);

            sql = "delete from tblPropertyImages where PropertyID = '" + lblID.Text.Trim() + "'";
            Database.executeQuery(sql);
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            Response.Redirect("AdminBookedPropertiesList.aspx");
        }
    }
}