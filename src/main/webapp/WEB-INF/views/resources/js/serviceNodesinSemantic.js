$(document).on('click', '#buttonPerUploadNuovoFile', function (e)
{

    $("#hidden-new-file").change(function()
    {
        var nomiDITuttiIFile = "File da caricare:<br>";
        var i =0;
        for(i=0; i< $('#hidden-new-file')[0].files.length ; i++)
        {
            nomiDITuttiIFile =nomiDITuttiIFile+ "<br>"+$("#hidden-new-file")[0].files[i].name;
        }
        $("#file-name").html(nomiDITuttiIFile);
    });


    var idHiddenPereID = document.getElementById("idCartellaPadre");
    var cartellaPadre = idHiddenPereID.getAttribute("value");


    $('#modalid').modal
    ({
            closable  : false,
            onDeny    : function()
            {
                window.location.reload();
            },
            onHide: function()
            {
                window.location.reload();
            },
            onApprove : function()
            {
                if(( $("#hidden-new-file")[0].files.length != 0))
                {

                    var i=0;
                    for(i=0; i< $("#hidden-new-file")[0].files.length ; i++)
                    {
                        var formData = new FormData();
                        formData.append('file', $("#hidden-new-file")[0].files[i]);
                        formData.append('idCartella', cartellaPadre);
                        var nomeFile =  $("#hidden-new-file")[0].files[i].name;
                        var ajaxReq = $.ajax
                        ({

                            url: 'http://localhost:8010/node/fileUpload',
                            type: 'POST',
                            data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            xhr: function () {
                                var xhr = $.ajaxSettings.xhr();

                                //Set onprogress event handler
                                xhr.upload.onprogress = function (event)
                                {
                                    var perc = Math.round((event.loaded / event.total) * 100);
                                    $('#progressBar')
                                        .progress({percent: perc});

                                };
                                return xhr;
                            },
                            beforeSend: function (xhr)
                            {

                            }
                        });

                        // Called on success of file upload
                        ajaxReq.done(function (msg)
                        {
                            // console.log(msg);
                            $.uiAlert({
                                textHead: 'Carica file',
                                text: 'I file sono stati caricati con successo: '+ nomeFile,
                                bgcolor: '#19c3aa',
                                textcolor: '#fff',
                                position: 'top-center', // top And bottom ||  left / center / right
                                icon: 'checkmark box',
                                time: 3
                            });
                        });

                        // Called on failure of file upload
                        ajaxReq.fail(function (jqXHR) {
                            $.uiAlert({
                                textHead: 'Errore',
                                text: 'Si è verificato un errore nel caricare il seguente file'+ nomeFile,
                                bgcolor: '#ff0000',
                                textcolor: '#000000',
                                position: 'top-center', // top And bottom ||  left / center / right
                                icon: 'remove circle',
                                time: 3
                            });

                        });
                    }//del for
                }
                else
                {
                    $.uiAlert({
                        textHead: 'Errore',
                        text: 'Devi selezionare un file',
                        bgcolor: '#ff0000',
                        textcolor: '#000000',
                        position: 'top-center', // top And bottom ||  left / center / right
                        icon: 'remove circle',
                        time: 3
                    });


                }


                return false;
            }
        })
        .modal('show')
} );



$(document).on('click', '#buttonPerCreareNuovaCartella', function (e)
{
    var idHiddenPereID = document.getElementById("idCartellaPadre");

});


