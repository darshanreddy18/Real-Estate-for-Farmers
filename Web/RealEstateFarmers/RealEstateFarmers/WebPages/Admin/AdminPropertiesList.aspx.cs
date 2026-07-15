using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RealEstateFarmers.WebPages.Admin
{
    public partial class AdminPropertiesList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                //string sql = "Select distinct(Category) from tblProperties where Status!='Booked' or Status!='Accepted'";
                string sql = "Select distinct(Category) from tblProperties";
                obj.filllist(ddlCategory, sql);
            }
        }

        protected void ddlCategory_SelectedIndexChanged(object sender, EventArgs e)
        {
            string sql = "Select * from tblProperties where Category='" + ddlCategory.SelectedValue + "'";
            obj.fill(grdProperties, sql, lblerror);
        }
    }
}