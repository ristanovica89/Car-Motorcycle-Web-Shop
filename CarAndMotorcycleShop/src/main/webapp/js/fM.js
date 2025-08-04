const contextPath = window.contextPath;

   document.getElementById('car-brand').addEventListener('change', function () {
     let brand = this.value;
     let modelSelect = document.getElementById('car-model');
     modelSelect.innerHTML = "<option value=''>-- Model --</option>";
     modelSelect.disabled = true;

     if (brand !== "") {
       fetch(contextPath + "/api", {
         method: "POST",
         headers: {
           "Content-Type": "application/json"
         },
         body: JSON.stringify({ brand: brand })
       })
       .then(res => res.json())
       .then(models => {
    	   if(models.length>0){
			models.forEach(model =>{
			let opt = document.createElement("option")
			opt.value = model;
			opt.text = model;
			modelSelect.appendChild(opt);
			});
			modelSelect.disabled = false;	
    	   }
       })
       .catch(err => {
         console.log("Greska:", err);
       });
     }
   });

   document.getElementById('motorcycle-brand').addEventListener('change', function () {
     let brand = this.value;
     let modelSelect = document.getElementById('motorcycle-model');
     modelSelect.innerHTML = "<option value=''>-- Model --</option>";
     modelSelect.disabled = true;

     if (brand !== "") {
       fetch(contextPath + "/api", {
         method: "POST",
         headers: {
           "Content-Type": "application/json"
         },
         body: JSON.stringify({ brand: brand })
       })
       .then(res => res.json())
       .then(models => {
    	   if(models.length>0){
   			models.forEach(model =>{
   			let opt = document.createElement("option")
   			opt.value = model;
   			opt.text = model;
   			modelSelect.appendChild(opt);
   			});
   			modelSelect.disabled = false;		
         }
       })
       .catch(err => {
         console.log("Greska:", err);
       });
     }
   });
