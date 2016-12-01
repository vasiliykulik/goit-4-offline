-- #5
SELECT 
    name, prize
FROM
    pizzeria.components
WHERE
    prize = (SELECT 
            MAX(prize)
        FROM
            pizzeria.components) 
UNION ALL SELECT 
    name, prize
FROM
    pizzeria.components
WHERE
    prize = (SELECT 
            MIN(prize)
        FROM
            pizzeria.components);
-- #5 advanced

SELECT 
    name, prize
FROM
    pizzeria.components pz
        JOIN
    (SELECT 
        MAX(prize) prize
    FROM
        pizzeria.components UNION ALL SELECT 
        MIN(prize) prize
    FROM
        pizzeria.components) a ON (pz.prize = a.prize);

-- #6

SELECT 
    cp.name, SUM(pzc.amount * cp.PRIZE)
FROM
    pizzeria.pizza pz
        JOIN
    pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
        JOIN
    pizzeria.components cp ON (cp.id = pzc.component_id)
group by cp.name;

-- #7

SELECT 
    pz.name, SUM(pzc.amount * cp.PRIZE)
FROM
    pizzeria.pizza pz
        JOIN
    pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
        JOIN
    pizzeria.components cp ON (cp.id = pzc.component_id)
GROUP BY pz.name;

-- #8
SELECT 
    pz.name
FROM
    pizza pz
WHERE
    NOT EXISTS( SELECT 
            1
        FROM
            pizza_components pc
                JOIN
            components c ON (c.id = pc.component_id)
        WHERE
            pc.pizza_id = pz.id
                AND c.name = 'Garlic');

-- #8 (alternative)            
SELECT 
    pz.name
FROM
    pizza pz
WHERE
    pz.id NOT IN (SELECT 
            pc.pizza_id pizza_id
        FROM
            pizza_components pc
                JOIN
            components c ON (c.id = pc.component_id)
        WHERE
            c.name = 'Garlic');
   
-- #9 
   
SELECT 
    name
FROM
    (SELECT 
        pz.name, SUM(pzc.amount * cp.PRIZE) s
    FROM
        pizzeria.pizza pz
    JOIN pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
    JOIN pizzeria.components cp ON (cp.id = pzc.component_id)
    GROUP BY pz.name
    ORDER BY s DESC) a
LIMIT 1;            
            
-- #10 

SELECT 
    po.order_id, SUM(a.s * po.count)
FROM
    (SELECT 
        pz.id, SUM(pzc.amount * cp.PRIZE) s
    FROM
        pizzeria.pizza pz
    JOIN pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
    JOIN pizzeria.components cp ON (cp.id = pzc.component_id)
    GROUP BY pz.id) a
        JOIN
    pizzeria.pizza_order po ON (po.pizza_id = a.id)
ORDER BY order_id;

-- #10 (alt) 
SELECT 
        pz.id, SUM(pzc.amount * cp.PRIZE) * po.count s
    FROM
        pizzeria.pizza pz
    JOIN pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
    JOIN pizzeria.components cp ON (cp.id = pzc.component_id)
    JOIN pizzeria.pizza_order po on (po.pizza_id = pz.id)
ORDER BY order_id;
            
-- #11 mysql

SELECT 
    cp.name, sum(pzc.amount * po.count)
FROM
    pizzeria.pizza pz
        JOIN
    pizzeria.pizza_components pzc ON (pz.id = pzc.pizza_id)
        JOIN
    pizzeria.components cp ON (cp.id = pzc.component_id)
        JOIN
    pizzeria.pizza_order po ON (po.pizza_id = pz.id)
        JOIN
    pizzeria.orders o ON o.id = po.order_id
where 
      o.create_date >= DATE_ADD(curdate(), INTERVAL -1 DAY) and o.create_date < curdate()
group BY cp.name;

-- #12 mysql 

SELECT 
    o.id, o.create_date
FROM
    pizzeria.orders o
where 
    o.create_date >= DATE_ADD(curdate(), INTERVAL -1 DAY) and o.create_date < curdate();
            
-- #13 
SELECT 
    name
FROM
    (SELECT 
        pz.name, COUNT(1) cnt
    FROM
        pizzeria.pizza pz
    JOIN pizzeria.pizza_order po ON (po.pizza_id = pz.id)
    JOIN pizzeria.orders o ON o.id = po.order_id
    GROUP BY pz.name
    ORDER BY cnt) a
LIMIT 1


