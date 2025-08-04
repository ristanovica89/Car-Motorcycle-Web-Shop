function deleteVehicleById(id, contextPath){
	fetch(contextPath + "/api/vehicle", {
	  method: "DELETE",
	  headers: {
	    "Content-Type": "application/json"
	  },
	  body: JSON.stringify({ id: id })
	})
	.then(res => res.json())
	.then(data => {
	  if (data.status === "success") {
	    alert("Deleted!");
	    location.reload();
	  } else {
	    alert("Error");
	  }
	});

}
//kada se bude razradio projekat
/*function deleteUserById(id, contextPath){
	fetch(contextPath + "/api/user", {
	  method: "DELETE",
	  headers: {
	    "Content-Type": "application/json"
	  },
	  body: JSON.stringify({ id: id })
	})
	.then(res => res.json())
	.then(data => {
	  if (data.status === "success") {
	    alert("Deleted!");
	    location.reload();
	  } else {
	    alert("Error");
	  }
	});

}
*/





