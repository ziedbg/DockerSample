const reverseWords = require('./reverseWords');

describe('reverseWords', () => {
    test('renverse les lettres de chaque mot', () => {
    expect(reverseWords('Bonjour le monde')).toBe('ruojnoB el ednom');
    expect(reverseWords('Hello world')).toBe('olleH dlrow');
});

test('génère une exception pour une phrase vide', () => {
    expect(() => reverseWords('')).toThrow('La phrase ne peut pas être vide');
});
});
