insert into users (
    first_name,
    last_name,
    patronymic,
    birth_date,
    description,
    phone,
    email,
    password
)
values (
        'ADMIN',
        '-',
        '-',
        to_timestamp('26.07.2020','dd.mm.yyyy'),
        'Super admin !',
        '+7-911-123-12-12',
        'test@mail.ru',
        'some_password'
       );
