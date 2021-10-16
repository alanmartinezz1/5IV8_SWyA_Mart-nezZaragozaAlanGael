# crypto-aes192
Simple encrypt and decrypt tool for generate hash string

## Installation
npm i crypto-aes192

## Usage
```
const password = 'abcde12345';
const cryptoAes192 = require('crypto-aes192')(password);

let test = "marshall"
let e = cryptoAes192.encrypt(test);
let d = cryptoAes192.decrypt(e);
console.log(test, e, d);
```