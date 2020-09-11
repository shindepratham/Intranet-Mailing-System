(function () {
	var filesUpload = document.getElementById("files-upload"),
		dropArea = document.getElementById("drop-area"),
		fileList = document.getElementById("file-list"),
		compose=document.getElementById("submitForm");
	  var fileIdList = [];	
	  function uploadFile (file,row) {
			var div1 = document.createElement("div"),
			div2 = document.createElement("div"),
			img,
			progressBarContainer = document.createElement("div"),
			progressBar = document.createElement("div"),
			reader,
			xhr,
			fileInfo;
		    div1.className+="col-xs-6";
		row.appendChild(div1);	
		progressBarContainer.className = "progress-bar-container";
		progressBar.className = "progress-bar";
		progressBarContainer.appendChild(progressBar);
		div1.appendChild(progressBarContainer);
		
		/*
			If the file is an image and the web browser supports FileReader,
			present a preview in the file list
		*/
		if (typeof FileReader !== "undefined" && (/image/i).test(file.type)) {
			img = document.createElement("img");
			div1.appendChild(img);
			img.className = "image-files";	
			reader = new FileReader();
			reader.onload = (function (theImg) {
				return function (evt) {
					theImg.src = evt.target.result;
				};
			}(img));
			reader.readAsDataURL(file);
		}
		div1.appendChild(div2);
		
		// Uploading - for Firefox, Google Chrome and Safari
		xhr = new XMLHttpRequest();
		
		// Update progress bar
		xhr.upload.addEventListener("progress", function (evt) {
			if (evt.lengthComputable) {
				progressBar.style.width = (evt.loaded / evt.total) * 100 + "%";
			}
			else {
				// No data to calculate on
			}
		}, false);
		
		// File uploaded
		xhr.addEventListener("load", function () {
			progressBarContainer.className += " uploaded";
			progressBar.innerHTML = "Uploaded!";
             fileIdList.push(xhr.getResponseHeader("fileId"));		 
		}, false);
		
		xhr.open("post", "uploadFile.do", true);
		var formData = new FormData();
		formData.append("myFile", file);
		xhr.send(formData);
		
		// Present file info and append it to the list of files
		fileInfo = "<div><strong>Name:</strong> " + file.name + "</div>";
		fileInfo += "<div><strong>Size:</strong> " + parseInt(file.size / 1024, 10) + " kb</div>";
		fileInfo += "<div><strong>Type:</strong> " + file.type + "</div>";
		div2.innerHTML = fileInfo;
		fileList.appendChild(row);
	}
	
	function traverseFiles (files) {
		if (typeof files !== "undefined") {
			for (var i=0, l=files.length; i<l; i++) {
				var row;
				if(i%2==0){
					row=document.createElement("div");
					row.className += "row";
				}	
					
				uploadFile(files[i],row);
			}
		}
		else {
			fileList.innerHTML = "No support for the File API in this web browser";
		}	
	}
	
	filesUpload.addEventListener("change", function () {
		traverseFiles(this.files);
	}, false);
	
	dropArea.addEventListener("dragleave", function (evt) {
		var target = evt.target;
		
		if (target && target === dropArea) {
			this.className = "";
		}
		evt.preventDefault();
		evt.stopPropagation();
	}, false);
	
	dropArea.addEventListener("dragenter", function (evt) {
		this.className = "over";
		evt.preventDefault();
		evt.stopPropagation();
	}, false);
	
	dropArea.addEventListener("dragover", function (evt) {
		evt.preventDefault();
		evt.stopPropagation();
	}, false);
	
	dropArea.addEventListener("drop", function (evt) {
		traverseFiles(evt.dataTransfer.files);
		this.className = "";
		evt.preventDefault();
		evt.stopPropagation();
	}, false);										

  function addHiddenField(props) {
  Object.keys(props).forEach(fieldName => {
    var field = compose[fieldName];
    if (!field) {
      field = document.createElement('input');
      field.type = 'hidden';
      field.name = fieldName;
	  field.value = props[fieldName];
      compose.appendChild(field);
    }
  });
}

  compose.addEventListener('submit', () => {
  addHiddenField({
   "fileidList": fileIdList.join()
  });
});
})();
