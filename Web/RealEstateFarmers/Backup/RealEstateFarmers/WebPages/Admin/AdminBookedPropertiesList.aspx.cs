using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RealEstateFarmers.WebPages.Admin
{
    public partial class AdminBookedPropertiesList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();
        
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                //string sql = "Select distinct(Category) from tblProperties where Status!='Booked' or Status!='Accepted'";
                string sql = "Select distinct(Status) from tblProperties where Status!='New'";
                obj.filllist(ddlStatus, sql);
            }
        }

        protected void ddlStatus_SelectedIndexChanged(object sender, EventArgs e)
        {
            string sql = "Select * from tblProperties where Status='" + ddlStatus.SelectedValue + "'";
            obj.fill(grdProperties, sql, lblerror);
        }
    }
}