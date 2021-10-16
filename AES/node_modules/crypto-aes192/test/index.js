var crypto = require("../index.js")('123456798');
var chai = require('chai');

var expect = chai.expect;
describe('crypto', function() {
    let test = 'marshall'
    let e = crypto.encrypt(test);
    let d = crypto.decrypt(e);
    describe('encrypt', function() {
        it('should return encrtpyed text', function() {
            expect(typeof(e)).to.equal("string");
        });
    });

    describe('decrypt', function() {
        it('should return decrtpyed text', function() {
            expect(d).to.equal(test);
        });
    });


});