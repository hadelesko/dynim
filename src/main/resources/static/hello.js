

/*
function onCheckboxChanged(checked){
	if(checked)
		$("#hiddenRow").fadeIn(1500 ,'linear');

	else $("#hiddenRow").fadeOut(900 ,'linear');
}


<html>
<head>
<title>Form</title>
<link rel="stylesheet" href="style.css" />
<script src="jquery.js"></script>
<script src="scripts.js"></script>
</head>
<form action="" method="POST">
<table>
<tbody>
<tr><td colspan="2"><h1 id="form_title">Form</h1></td></tr>
<tr><td>Name : </td><td><input type="text" name="name" placeholder="Enter Name" /></td></tr>
<tr><td>Email : </td><td><input type="email" name="email" placeholder="Enter Email" /></td></tr>
<tr><td>Already a Member : </td><td><input type="checkbox" onchange="onCheckboxChanged(this.checked)" name="member" /></td></tr>
<tr id="hiddenRow" style="display:none;"><td>Username : </td><td><input type="text" name="username" placeholder="Enter Username" /></td></tr>
<tr><td colspan="2"><input type="submit" name="submit" value="Submit" /></td></tr>
</tbody>
</table>
</form>
</html>*/


 function myFunction() {
    var optionlocationId = document.getElementById("locationiId");
    var inputfieldid = document.getElementById("locid");

    var optionlocationName=document.getElementById("locationName");
    var inpufieldlocationName=document.getElementById("locname");

    var optionlocationStock=document.getElementById("locationStock");
    var inpufieldlocationStock=document.getElementById("locstock");

    if (optionlocationId.selected == true){
        inputfieldid.style.display = "block";
    } else {
      inputfieldid.style.display = "none";
    }

    if (optionlocationName.selected == true){
        inputfiellocationName.style.display = "block";
    } else {
      inputfieldlocationName.style.display = "none";
    }

     if (optionlocationStock.selected == true){
        inputfieldlocationStock.style.display = "block";
    } else {
      inputfieldlocationStock.style.display = "none";
    }
}




Checkbox1: <input type="checkbox" id="myCheck1"  onclick="myFunction1()">
<input  id="text1" style="display:none"/ placeholder="Checkbox1 is CHECKED!">

<p>Display some text when the checkbox is checked:</p>

Checkbox: <input type="checkbox" id="myCheck"  onclick="myFunction1()">

<p id="text" style="display:none">Checkbox is CHECKED!</p>


/*<select>
	<option>
    	University: <input type="checkbox"/>
    </option>
</select>*/


<script>
function myFunction1() {
    var checkBox = document.getElementById("myCheck");
    var text = document.getElementById("text");

    var checkBox1 = document.getElementById("myCheck1");
    var text1 = document.getElementById("text1")

    if (checkBox.checked == true){
        text.style.display = "block";
    } else {
       text.style.display = "none";
    }

    if (checkBox1.checked == true){
        text1.style.display = "block";
    } else {
       text1.style.display = "none";
    }
}
</script>

</body>
</html>

function onlyOne(checkbox) {
    var checkboxes = document.getElementsByName('check')
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false
    })
}

<input type="checkbox" name="check" onclick="onlyOne(this)">
<input type="checkbox" name="check" onclick="onlyOne(this)">
<input type="checkbox" name="check" onclick="onlyOne(this)">
<input type="checkbox" name="check" onclick="onlyOne(this)">