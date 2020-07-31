create table company_employee(
    company_id int,
    employee_id int,
    primary key(company_id, employee_id),
    foreign key(company_id) references company(id),
    foreign key(employee_id) references employee(id)
)