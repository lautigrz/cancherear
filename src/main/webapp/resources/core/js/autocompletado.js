function searchPlaces() {
    const input = document.getElementById('ubicacion').value;
    if (input.length < 3) {
        // No hacemos la petición si el texto es muy corto
        document.getElementById('suggestions').innerHTML = "";
        return;
    }

    const url = `https://google-place-autocomplete-and-place-info.p.rapidapi.com/maps/api/place/autocomplete/json?input=${input}`;
    const options = {
        method: 'GET',
        headers: {
            'x-rapidapi-key': '04dde888d0msh628778a01066c96p1d89e5jsn6db446e039ef',
            'x-rapidapi-host': 'google-place-autocomplete-and-place-info.p.rapidapi.com'
        }
    };

    fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la respuesta de la API');
            }
            return response.json();
        })
        .then(data => {
            const suggestions = document.getElementById('suggestions');
            suggestions.innerHTML = "";


            if (data.predictions && data.predictions.length > 0) {
                data.predictions.forEach(prediction => {

                    const suggestionItem = document.createElement('div');
                    suggestionItem.textContent = prediction.description;
                    suggestionItem.onclick = () => {

                        document.getElementById('ubicacion').value = prediction.description;

                        suggestions.innerHTML = "";
                    };
                    suggestions.appendChild(suggestionItem);
                });
            } else {

                suggestions.innerHTML = "<div>No se encontraron resultados</div>";
            }
        })
        .catch(error => {
            console.error('Error al realizar la solicitud:', error);
        });
}