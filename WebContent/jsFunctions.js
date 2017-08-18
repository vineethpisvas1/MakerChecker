function isAlphanumeric(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122)) {
    	return true;
    }
    return false;
}
function isInt(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode>=48 && charCode<=57) {
    	return true;
    }
    return false;
}
function isAlphabet(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if((charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122)) {
    	return true;
    }
    return false;
}
function isAlphanumericSpace(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32) {
    	return true;
    }
    return false;
}
function isAlphabetSpace(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if((charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32) {
    	return true;
    }
    return false;
}
function checkSize(element) {
	var str = element.value;
	var name = element.name;
	var n = str.length;
	if(name.localeCompare("pincode")==0) {
		if(n<=5) {
	    	return true;
	    }
	    return false;
	} else if(name.localeCompare("contactNumber")==0) {
		if(n<=19) {
	    	return true;
	    }
	    return false;
	} else if(name.localeCompare("customerCode")==0) {
		if(n<=9) {
	    	return true;
	    }
	    return false;
	} else if(name.localeCompare("customerName")==0 || name.localeCompare("modifiedBy")==0 || name.localeCompare("authorizedBy")==0 || name.localeCompare("createdBy")==0) {
		if(n<=29) {
	    	return true;
	    }
	    return false;
	} else if(name.localeCompare("email")==0 || name.localeCompare("primaryContactPerson")==0 || name.localeCompare("address1")==0 || name.localeCompare("address2")==0) {
		if(n<=99) {
	    	return true;
	    }
	    return false;
	} else {
		return true;
	}
}
function specificRecordView() {
	document.getElementById("customerCodeDiv").style.display="block";
	document.getElementById("customerCode").required=true;
}
function allRecordsView() {
	document.getElementById("customerCodeDiv").style.display="none";
	document.getElementById("customerCode").required=false;
}
function showHide(element) {
	var str = element.value;
	var name = element.id;
	if(str.localeCompare("on")==0) {
		if(name.localeCompare("flag")==0) {
			document.getElementsByName(name)[1].disabled = true;
			document.getElementsByName(name)[1].enabled = false;
			element.value = "off";
			document.getElementsByName(name)[0].disabled = true;
			document.getElementsByName(name)[0].enabled = false;
			document.getElementsByName(name)[1].required = false;
			document.getElementsByName(name)[0].required = false;
		} else {
			document.getElementsByName(name)[1].disabled = true;
			document.getElementsByName(name)[1].enabled = false;
			document.getElementsByName(name)[1].value = "";
			document.getElementsByName(name)[1].required = false;
			element.value = "off";
		}
	} else if(str.localeCompare("off")==0){
		if(name.localeCompare("flag")==0) {
			document.getElementsByName(name)[1].disabled = false;
			document.getElementsByName(name)[1].enabled = true;
			element.value = "on";
			document.getElementsByName(name)[0].disabled = false;
			document.getElementsByName(name)[0].enabled = true;
			document.getElementsByName(name)[1].required = true;
			document.getElementsByName(name)[0].required = true;
		} else {
			document.getElementsByName(name)[1].disabled = false;
			document.getElementsByName(name)[1].enabled = true;
			document.getElementsByName(name)[1].value = "";
			document.getElementsByName(name)[1].required = true;
			element.value = "on";
		}
	}
}
function resetUpdate() {
	document.getElementsByName("customerName")[1].disabled = true;
	document.getElementsByName("customerName")[1].enabled = false;
	document.getElementsByName("customerName")[1].required = false;
	
	document.getElementsByName("address1")[1].disabled = true;
	document.getElementsByName("address1")[1].enabled = false;
	document.getElementsByName("address1")[1].required = false;
	
	document.getElementsByName("address2")[1].disabled = true;
	document.getElementsByName("address2")[1].enabled = false;
	document.getElementsByName("address2")[1].required = false;
	
	document.getElementsByName("pincode")[1].disabled = true;
	document.getElementsByName("pincode")[1].enabled = false;
	document.getElementsByName("pincode")[1].required = false;
	
	document.getElementsByName("email")[1].disabled = true;
	document.getElementsByName("email")[1].enabled = false;
	document.getElementsByName("email")[1].required = false;
	
	document.getElementsByName("contactNumber")[1].disabled = true;
	document.getElementsByName("contactNumber")[1].enabled = false;
	document.getElementsByName("contactNumber")[1].required = false;
	
	document.getElementsByName("primaryContactPerson")[1].disabled = true;
	document.getElementsByName("primaryContactPerson")[1].enabled = false;
	document.getElementsByName("primaryContactPerson")[1].required = false;
	
	document.getElementsByName("flag")[0].disabled = true;
	document.getElementsByName("flag")[0].enabled = false;
	document.getElementsByName("flag")[0].required = false;
	document.getElementsByName("flag")[1].disabled = true;
	document.getElementsByName("flag")[1].enabled = false;
	document.getElementsByName("flag")[1].required = false;
}
function checkOperation(element) {
	var id = element.id;
	document.getElementById("recordRow").value = id;
}