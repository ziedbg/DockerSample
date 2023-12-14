const incrementIfPositive = require('./incrementIfPositive');

describe('incrementIfPositive', () => {
    test('increments positive numbers by 1', () => {
    expect(incrementIfPositive(1)).toBe(2);
    expect(incrementIfPositive(5)).toBe(6);
});

test('returns 0 for non-positive numbers', () => {
    expect(incrementIfPositive(0)).toBe(0);
expect(incrementIfPositive(-1)).toBe(0);
expect(incrementIfPositive(-5)).toBe(0);
});
});
