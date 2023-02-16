function send() {
    document.getElementById('myForm').submit();
}

function makeDisable() {
    let jsIdCategories = document.getElementById('categoryList');
    let jsIdTransactions = document.getElementById('transactionList');
    console.log(jsIdTransactions.value)
    jsIdCategories.disabled = jsIdTransactions.value === 'INCOME';
}