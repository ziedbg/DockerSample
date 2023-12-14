function reverseWords(sentence) {
    if (sentence === "") {
        throw new Error("La phrase ne peut pas être vide");
    }
    return sentence.split(" ").map(word => word.split("").reverse().join("")).join(" ");
}

module.exports = reverseWords;
