insert into hotels (id, address, name, phone, pois)
values ('AZ123', { street: '1600 Pennsylvania Ave NW',
    city: 'Washington',
    state_or_province: 'Washington',
    postal_code: '20500'}, 'AZ123-name', '12345',
        {'poi-1', 'pois-2', 'pois-3'});

SELECT * FROM hotels where id = 'AZ123';