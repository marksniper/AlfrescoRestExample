$(document).on('click', '#listaFileFolder li', function () {
    $('#checkPostOperationForUpload').val("no");
    $.ajax({
        url: "http://localhost:8010/node/info/" + this.id,
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        crossDomain: true,
        success: function (data) {
            var modal = document.getElementById('myModal');

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            var datacreazione = new Date("" + data['entry']['createdAt']);

            var dataModifica = new Date("" + data['entry']['modifiedAt']);


            $("#idNodoPerAltreOperazioni").val("" + data['entry']['id']);

            var infoNodo = "";
            var uploadFileHtml = "";
            var aggiustoMinuto ="";
            var aggiustoOra ="";
            if (data['entry']['isFolder'])
            {

                if(datacreazione.getMinutes()<10)
                {
                    // console.log ("GET MINUTES: "+dataModifica.getMinutes());
                    aggiustoMinuto="0";
                }
                if(datacreazione.getHours()<10)
                {
                    // console.log ("GET MINUTES: "+dataModifica.getMinutes());
                    aggiustoOra="0";
                }


                infoNodo = "Creato da: "
                    + data['entry']['createdByUser']['displayName'] + " in data: " + datacreazione.getDate() + "/" + (datacreazione.getMonth() + 1) + "/" + datacreazione.getFullYear() + " "
                    + aggiustoOra + datacreazione.getHours() + ":" + aggiustoMinuto + datacreazione.getMinutes()
                    + ".<br>";
                if(dataModifica.getMinutes()<10)
                {

                    aggiustoMinuto="0";
                }
                else
                {
                    aggiustoMinuto="";
                }
                if(dataModifica.getHours()<10)
                {
                    aggiustoOra="0";
                }
                else
                {
                    aggiustoOra="";
                }

                infoNodo = infoNodo+ "Modificato da: " + data['entry']['modifiedByUser']['displayName'] + " in data: " + dataModifica.getDate() + "/" + (dataModifica.getMonth() + 1) + "/" + dataModifica.getFullYear() + " "
                    + aggiustoOra + dataModifica.getHours() + ":" + aggiustoMinuto + dataModifica.getMinutes()
                    + ".<br>";
                uploadFileHtml =
                    " <form action=\"fileUpload\" method=\"post\" enctype=\"multipart/form-data\">  <label>Select file</label>\n" +
                    "                                <br><br>\n" +
                    "                                        <input type=\"file\" id=\"inputFileInput\" name=\"file\">\n" +
                    "                                <br><br>\n" +
                    "                                        <button class=\"btn btn-primary\" type=\"submit\" id=\"uploadFile\">Upload</button>\n" +
                    "                                <br />\n" +
                    "\n" +
                    "                              </form>  <!-- Bootstrap Progress bar https://www.boraji.com/spring-4-mvc-jquery-ajax-file-upload-example-with-progress-bar -->\n" +
                    "                              <br />  <div class=\"progress\">\n" +
                    "                                    <div id=\"progressBar\" class=\"progress-bar progress-bar-striped progress-bar-animated\"  role=\"progressbar\"\n" +
                    "                                         aria-valuenow=\"0\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 0%\">0</div>\n" +
                    "                                </div>\n" +
                    "\n" +
                    "                                <!-- Alert -->\n" +
                    "                                <div id=\"alertMsg\" style=\"color: black;font-size: 18px;\"></div>\n";

            }
            if (data['entry']['isFile']) {
                var descrizione = data['entry']['properties']['cm:description'];
                if (descrizione == undefined) {
                    descrizione = "Non inserita";
                }

                if(datacreazione.getMinutes()<10)
                {
                    aggiustoMinuto="0";
                }
                if(datacreazione.getHours()<10)
                {
                    aggiustoOra="0";
                }

                infoNodo = "Creato da: "
                    + data['entry']['createdByUser']['displayName'] + " in data: " + datacreazione.getDate() + "/" + (datacreazione.getMonth() + 1) + "/" + datacreazione.getFullYear() + " "
                    + aggiustoOra+ datacreazione.getHours() + ":" + aggiustoMinuto + datacreazione.getMinutes()
                    + ".<br>";
                if(dataModifica.getMinutes()<10)
                {
                    aggiustoMinuto="0";
                }
                else
                {
                    aggiustoMinuto="";
                }
                if(dataModifica.getHours()<10)
                {
                    aggiustoOra="0";
                }
                else
                {
                    aggiustoOra="";
                }

                infoNodo = infoNodo
                    + "Modified ny: " + data['entry']['modifiedByUser']['displayName'] + " in data: " + dataModifica.getDate() + "/" + (dataModifica.getMonth() + 1) + "/" + dataModifica.getFullYear() + " "
                    + aggiustoOra +dataModifica.getHours() + ":" +aggiustoMinuto+ dataModifica.getMinutes()
                    + ".<br>"
                    + "Description: " + descrizione
                    + ".<br>"
                    + "File type: " + data['entry']['content']['mimeTypeName']
                    + ".<br>"
                    + "File size: " + bytesToSize(data['entry']['content']['sizeInBytes'])
                    + ".<br>"
                ;


            }


            var nomeNodo = "Nome: " + data['entry']['name'];

            span.onclick = function () {
                /*modal.style.display = "none";*/
                $('#myModal').modal('hide');
                if (uploadFileHtml != "" && ($('#checkPostOperationForUpload').val() == "yes")) {
                    location.reload();
                }

            };
            window.onclick = function (event) {
                if (event.target == modal) {
                    $('#myModal').modal('hide');
                    if (uploadFileHtml != "" && ($('#checkPostOperationForUpload').val() == "yes")) {
                        location.reload();
                    }
                }
            };
            $("#informazioniNodo").text("");

            $("#informazioniNodo").append(infoNodo);
            $("#nomedelnodo").text(nomeNodo);

            $("#insersciFile").text("");
            $("#insersciFile").append(uploadFileHtml);

            $('#myModal').modal('show');
        },
        error: function (err) {
        }
    });

});

function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return '0 Byte';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
}