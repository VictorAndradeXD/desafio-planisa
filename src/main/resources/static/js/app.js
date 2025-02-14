document.getElementById('benchmarkForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const progressBar = document.getElementById('progressBar');
    const progress = document.getElementById('progress');
    progressBar.style.display = 'block';

    const benchmark = {
        name: document.getElementById('name').value,
        country1: document.getElementById('country1').value,
        country2: document.getElementById('country2').value,
        startDate: document.getElementById('startDate').value,
        endDate: document.getElementById('endDate').value
    };

    fetch('/api/benchmarks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(benchmark)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao salvar o benchmark');
            }
        })
        .then(data => {
            progress.style.width = '100%';

            setTimeout(() => {
                window.location.href = '/';
            }, 1000);
        })
        .catch(error => {
            alert('Erro ao salvar o benchmark: ' + error.message);
            console.error('Erro:', error);
        });
});