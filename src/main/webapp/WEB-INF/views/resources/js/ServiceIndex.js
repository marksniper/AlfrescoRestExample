(function()
{
    $(document).ready(function()
    {

    });

    /**
     * Global Settings and Variables
     *
     */
    Prova =
    {
        fullName: function ()
        {
            return this.firstName + " " + this.lastName;
        },

        getCurrentUser: function ()
        {
            console.log("Sei in getCurrent user: " +Prova.fullName());
            return "sei getCurrent User";
        },
        setLastName: function (lastName)
        {
          this.lastName = lastName;
        },
        setFirstName: function (firstName)
        {
            this.firstName= firstName;
        },
        getLastName: function ()
        {
            return this.lastName;
        }

    };

    TreeView =
        {
            idParent:"",

            _getTreeView1: function ()
            {
                Prova.setFirstName("Marco");
                Prova.setLastName("Serinelli");
                console.log("sei in _getTreeView1" +Prova.fullName());
                return "sono il return di _getTreeView1";
            },

            _getTree: function (option, option1 , option2, callback, error)
            {
                //init operation
                console.log("ServiceIndex >>> option: "+option);
                console.log("ServiceIndex >>> option1: "+option1);
                for (i = 0; i < option2.length; i++)
                {
                    console.log((i+1) + ": " + option2[i]);
                }


                $.ajax({
                    url: "http://localhost:8010/node/treedirectory",
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    async: true,
                    crossDomain: true,
                    success: function (data)
                    {
                        callback (data);
                    },
                    error: function (err)
                    {

                           error(err);
                    }
                });
            },
            _getInfoNode(idParent,callback, error)
            {
                console.log("location.pathname: "+location.pathname);
                console.log(idParent);
                $.ajax
                ({
                    url: "http://localhost:8010/node/info/" + idParent,
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    async: true,
                    crossDomain: true,
                    success: function (data)
                    {
                        callback (data);
                    },
                    error: function (err)
                    {
                        error(err);
                    }
                });

            }


        };

})();