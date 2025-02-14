document.getElementById('showCases').addEventListener('click', function () {
    const country1 = document.getElementById('country1').value;
    const country2 = document.getElementById('country2').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;

    fetch(`/api/indicators/cases-difference?country1=${country1}&country2=${country2}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').innerHTML = `<p>Diferença de casos confirmados: ${data.difference}</p>`;
        })
        .catch(error => {
            alert('Erro ao calcular a diferença de casos: ' + error.message);
            console.error('Erro:', error);
        });
});

document.getElementById('showDeaths').addEventListener('click', function () {
    const country1 = document.getElementById('country1').value;
    const country2 = document.getElementById('country2').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;

    fetch(`/api/indicators/deaths-difference?country1=${country1}&country2=${country2}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').innerHTML = `<p>Diferença de mortes: ${data.difference}</p>`;
        })
        .catch(error => {
            alert('Erro ao calcular a diferença de mortes: ' + error.message);
            console.error('Erro:', error);
        });
});

document.getElementById('showFatalityRate').addEventListener('click', function () {
    const country1 = document.getElementById('country1').value;
    const country2 = document.getElementById('country2').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;

    fetch(`/api/indicators/fatality-rate?country1=${country1}&country2=${country2}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').innerHTML = `
                <p>Taxa de letalidade:</p>
                <ul>
                    <li>${country1}: ${data[country1].toFixed(2)}%</li>
                    <li>${country2}: ${data[country2].toFixed(2)}%</li>
                </ul>
            `;
        })
        .catch(error => {
            alert('Erro ao calcular a taxa de letalidade: ' + error.message);
            console.error('Erro:', error);
        });
});