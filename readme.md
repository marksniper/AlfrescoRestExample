
# Example web app that communicate with Alfresco API

## Installation

It is necessary to install Alfresco 5.2 and run its:

```
cd <alfresco-home>
./alfresco.sh start
```

## Functionality

This application permit to update a new file in Alfresco using Javascript and JQuery, using Bootstrap and Semantic user interface.

Moreover, a complete a personal file tree is shown in index. 

A complete tree is built using a Java class thanks to a Gson package.

## Other information

A Spring web app is implemented in order to communicate with Alfresco.

A complete tree rappresent a "MyFile" Alfresco folder.
