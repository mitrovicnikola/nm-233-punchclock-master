class IndexController {

    constructor() {
        this._checkIfIsLoggedIn();
        this._fetchEntries();
        this._applySubmitListener();
        this._applyLogoutListener();
    }

    _checkIfIsLoggedIn() {
        if (localStorage.getItem('authorization') === null) {
            window.location.href = '/login.html';
        }
    }

    _applyLogoutListener() {
        document.getElementById("logout").addEventListener('click', () => {
            localStorage.removeItem('authorization');
            window.location.href = '/login.html';
        });
    }

    async _fetchUsers() {
        fetch('/entries', {
            method: "GET",
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        })
            .then(res => res.json())
            .then(entries => this._insertIntoTemplate(entries));
    }

    _applySubmitListener() {
        document.getElementById("submit").addEventListener('click', (e) => {
            const entry = {
                checkIn: document.getElementById('check-in-input').value,
                checkOut: document.getElementById('check-out-input').value,
                activity: document.getElementById('check-check-input').value,
            };

            this._saveEntry(entry);
            document.getElementById('check-in-input').value = '';
            document.getElementById('check-out-input').value = '';
            document.getElementById('check-check-input').value = '';
        });
    }

    _insertIntoTemplate(entries) {
        document.querySelector('main').innerHTML = '';
        const main = document.querySelector('main');
        const template = document.querySelector('.template');
        entries.forEach(entry => {
            const clone = template.cloneNode(true);
            clone.querySelector('.check-in').innerHTML = `Check In: ${entry.checkIn}`;
            clone.querySelector('.check-out').innerHTML = `Check Out: ${entry.checkOut}`;
            clone.querySelector('.check-check').innerHTML = `Activity: ${entry.activity}`;
            clone.dataset.id = entry.id;
            clone.classList.remove('template');
            main.appendChild(clone);
        });
        this._applyDeleteListener();
    }

    _applyDeleteListener() {
        document.querySelectorAll('.delete').forEach(element => {
            element.addEventListener('click', e => {
                const section = e.target.closest('section.entry');
                this._deleteEntry(section.dataset.id);
            });
        });
    }

    _saveEntry(entry) {
        fetch("/entries", {
            method: 'POST',
            body: JSON.stringify(entry),
            headers: {
                'content-type': 'application/json',
                'Authorization': localStorage.getItem('authorization')
            }
        }).then(_ => this._fetchUsers());
    }

    _deleteEntry(id) {
        fetch(`/entries/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).then(_ => this._fetchUsers());
    }
}

new IndexController();