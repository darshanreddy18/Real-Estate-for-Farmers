using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RealEstateFarmers.WebPages.Admin
{
    public partial class AdminOwnersList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string sql = "Select * from tblOwners";
                obj.fill(grdOwners, sql, lblerror);
            }
        }
    }
}