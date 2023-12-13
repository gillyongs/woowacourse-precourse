import MenuData from '../src/menuData.js';

describe('MenuData class', () => {
  let menuData;

  beforeEach(() => {
    menuData = new MenuData();
  });

  test('extractMenuNames method', () => {
    const menuNames = menuData.extractMenuNames();
    expect(menuNames).toContain('양송이수프');
    expect(menuNames).toContain('티본스테이크');
  });

  test('findCategory method', () => {
    const category = menuData.findCategory('티본스테이크');
    expect(category).toBe('메인');
  });

  test('getMenuPrice method', () => {
    const price = menuData.getMenuPrice('초코케이크');
    expect(price).toBe(15000);
  });

  test('getMenuNamesByCategory method', () => {
    const dessertMenuNames = menuData.getMenuNamesByCategory('디저트');
    expect(dessertMenuNames).toContain('초코케이크');
  });

});
