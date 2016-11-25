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
GROUP BY pz.name
