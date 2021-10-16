'use strict';

const crypto = require('crypto');

class MidlandCrypto {
    constructor(privateKey) {
        this.privateKey = privateKey
    }

    getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    }

    combineKey(publicKey, privateKey) {
        return `${publicKey}${privateKey}`
    }

    encrypt(text) {
        let publicKey = this.getRandomInt(101010, 909090);
        let cipher = crypto.createCipher('aes192', this.combineKey(publicKey, this.privateKey));

        let encrypted = cipher.update(text, 'utf8', 'hex');
        encrypted += cipher.final('hex');

        return `${encrypted}-${publicKey}`;
    }

    decrypt(hash) {
        if (typeof hash !== 'string') return null;

        let hashData = hash.split("-")

        if (hashData.length === 2) {
            let encryptedString = hashData[0];
            let publicKey = hashData[1];

            if (publicKey.length !== 6) return null
            try {
                let decipher = crypto.createDecipher('aes192', this.combineKey(publicKey, this.privateKey));
                let decryptedRefId = decipher.update(encryptedString, 'hex', 'utf8');
                return decryptedRefId += decipher.final('utf8');
            } catch (err) {
                return null
            }
        } else {
            return null
        }
    }
}

module.exports = (privateKey) => {
    return new MidlandCrypto(privateKey)
}