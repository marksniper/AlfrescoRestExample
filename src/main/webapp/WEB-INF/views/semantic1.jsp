
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="res/js/other/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="res/js/other/semantic.min.js"></script>
    <script type="text/javascript" src="res/js/other/modal.min.js"></script>
    <script type="text/javascript" src="res/js/other/Semantic-UI-Alert.js"></script>


    <script type="text/javascript" src="res/js/createMyFileList.js"       ></script>
    <script type="text/javascript" src="res/js/infoNodeSemantic.js"       ></script>
    <script type="text/javascript" src="res/js/serviceNodesinSemantic.js" ></script>

    <link href="res/css/themes/icon.min.css" rel="stylesheet" type="text/css"/>
    <link href="res/css/themes/semantic.min.css" rel="stylesheet" type="text/css"/>
    <link href="res/css/themes/modal.min.css" rel="stylesheet" type="text/css"/>
    <link href="res/css/themes/Semantic-UI-Alert.css" rel="stylesheet" type="text/css">

    <style>
        * {
            box-sizing: border-box;
        }

        /* Create three un equal columns that floats next to each other */
        .column {
            float: left;
            padding: 10px;
        }

        .left, .right {
            width: 25%;
        }

        .middle {
            width: 50%;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
</head>
<body>
<nav>
    <%@include file="static/menu.jsp" %>
</nav>
<div class="row">

    <div class="column left">
        <div class="ui list">
            <div id="fileFolder">

            </div>
        </div>
    </div>
    <div class="column middle">
        <input type="hidden"  class="nodoCartellaPadre" id="idCartellaPadre" value=""/>
        <div class="ui two row grid">
            <div class="row">
                    <div class="ui two column grid">
                    <div class="column">
                        <button id="buttonPerCreareNuovaCartella" class="ui button" data-toggle="modal" data-target="#myModal">
                            <i class="huge icons">
                                <i class="teal folder icon"> </i>
                                <i class="corner black add icon"></i>
                            </i>
                            Create new folder
                        </button>
                    </div>
                    <div class="column">
                        <button id="buttonPerUploadNuovoFile" class="ui button">
                            <i class="huge icons">
                                <i class="olive file icon"> </i>
                                <i class="corner black add icon"></i>
                            </i>
                            Upload files
                        </button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="ui teal header">
                    <div class="column" id="nomeDellaCartellaInAnalisi">

                    </div>
                </div>
            </div>
        </div>

        <div id="informazioniNodiChild">


        </div>
    </div>
    <div class="column right">
        <div id="informazioniChildren" >

        </div>
    </div>

</div>

<div id="modalid" class="ui modal">
    <i class="close icon"></i>
    <div class="header">
       Carica file
    </div>
    <div class="description">
        <div class="ui fluid segment">
            <form class="ui form" action="fileUpload" method="post" enctype="multipart/form-data">
                   <div class="ui placeholder segment">
                       <div class="ui two column stackable center aligned grid">
                           <div class="middle aligned row">
                                   <div class="column">
                                       <label for="hidden-new-file" class="ui icon button">
                                           <i class="huge red upload icon"></i>
                                          Open file
                                       </label>
                                       <input type="file" name="file[]" id="hidden-new-file" style="display: none" multiple>
                                   </div>

                                   <div class="column">
                                       <label id="file-name"></label>
                                   </div>
                                   <div class="column">
                                       <div class="ui indicating progress" data-value="0" data-total="20" id="progressBar">
                                           <div class="bar">
                                               <div class="progress"></div>
                                           </div>
                                           <div class="label">Upload file</div>
                                       </div>
                                   </div>
                               <div class="column">

                               </div>

                                   <div class="column">
                                       <div class="actions">
                                           <div class="ui buttons">

                                                   <div class="ui positive right labeled icon button">
                                                   <i class="upload icon"></i>
                                                       Upload
                                                   </div>


                                                   <div class="ui red cancel button">
                                                   <i class="close icon"></i>
                                                       Close
                                                   </div>

                                           </div>
                                       </div>
                                   </div>


                           </div>
                       </div>
                   </div>
            </form>
        </div>
    </div>
</div>


</body>

</html>
