USE e_commerce;

INSERT INTO product_brand (uuid, name) VALUES
(UUID(), 'Faber-Castell'),
(UUID(), 'Bic'),
(UUID(), 'Tramontina'),
(UUID(), 'Dell'),
(UUID(), 'Moleskine');

INSERT INTO product_category (uuid, name) VALUES
(UUID(), 'Material Escolar'),
(UUID(), 'Informatica'),
(UUID(), 'Ferramentas'),
(UUID(), 'Papelaria'),
(UUID(), 'Material Escritorio');

INSERT INTO order_type (uuid, name) VALUES
(UUID(), 'Online'),
(UUID(), 'Telefone'),
(UUID(), 'Presencial');

INSERT INTO address (uuid, label, active, isprimary, zipcode, streetname, address_number, neighborhood, point_of_reference) VALUES
(UUID(), 'Casa Lucas Principal', 1, 1, '12345678', 'Rua das Flores', '100', 'Jardim Primavera', 'Prox. a padaria do centro'),
(UUID(), 'Apto Ana Vista Livre', 1, 1, '87654321', 'Av. Principal', '500-A', 'Centro', 'Frente ao banco grande'),
(UUID(), 'Escritorio Empresa X Filial', 1, 0, '99887766', 'Rua da Inovacao', '200', 'Tecnopolis Alfa', 'Edificio Alfa Bloco B');

INSERT INTO `user` (uuid, name, email, created_at, updated_at, nickname) VALUES
(UUID(), 'Lucas Domaradzki da Silva', 'lucas.dom@example.com', NOW(), NOW(), 'Lucao_xp'),
(UUID(), 'Ana Paula Silva Oliveira', 'ana.silva@example.com', NOW(), NOW(), 'Aninha_dev'),
(UUID(), 'Carlos Eduardo Rodrigues', 'carlos.edu@example.com', NOW(), NOW(), 'Cadu_tech');

INSERT INTO school_focus (uuid, level_name, description) VALUES
(UUID(), 'Fundamental I', 'Educacao 1o ao 5o ano'),
(UUID(), 'Fundamental II', 'Educacao 6o ao 9o ano'),
(UUID(), 'Ensino Medio', 'Preparacao Ensino Superior'),
(UUID(), 'Educacao Infantil', 'Primeiros anos de escola');

INSERT INTO school (uuid, name, active, address, school_focus_id) VALUES
(UUID(), 'Escola Municipal Sol Nascente', 1, 'Rua da Alegria, 123 - Bairro Sol', (SELECT id FROM school_focus WHERE level_name = 'Fundamental I')),
(UUID(), 'Colegio Alfa Prime', 1, 'Avenida dos Herois, 456 - Centro', (SELECT id FROM school_focus WHERE level_name = 'Ensino Medio')),
(UUID(), 'Centro Educacional Estrelinha', 1, 'Travessa da Lua, 78 - Bairro do Ceu', (SELECT id FROM school_focus WHERE level_name = 'Educacao Infantil'));

INSERT INTO product (uuid, name, product_brand_id, description, theme, color, dimensions, weight, suppler_price, final_price, special_price, special_price_amount, product_category_id) VALUES
(UUID(), 'Lapis de Cor 12 Cores Classic', (SELECT id FROM product_brand WHERE name = 'Faber-Castell'), 'Lapis de cor com 12 cores vibrantes. Ideal para uso escolar, nao toxico e com alta durabilidade. Perfeito para desenho e pintura, estimula a criatividade das criancas de forma segura e eficaz.', 'Cores Vibrantes', 'Multicolorido', '18x1x1 cm', 0.15, 12.50, 24.90, 0, NULL, (SELECT id FROM product_category WHERE name = 'Material Escolar')),
(UUID(), 'Caneta Esferografica Azul Cristal', (SELECT id FROM product_brand WHERE name = 'Bic'), 'Caneta esferografica de ponta fina, tinta azul. Escrita suave e sem falhas, ideal para o dia a dia na escola ou escritorio. Corpo transparente para controle de tinta.', 'Escrita Fluida', 'Azul', '15x0.5x0.5 cm', 0.01, 1.20, 2.50, 0, NULL, (SELECT id FROM product_category WHERE name = 'Material Escritorio')),
(UUID(), 'Caderno Espiral 96 Fls Universit', (SELECT id FROM product_brand WHERE name = 'Moleskine'), 'Caderno universitario espiral com capa dura, 96 folhas pautadas. Papel de alta qualidade, otimo para estudos e anotacoes. Design classico e resistente para o uso diario. Capa e folhas duraveis para todo o ano.', 'Estudos Essenciais', 'Verde', '20x28x1 cm', 0.30, 8.00, 15.99, 0, NULL, (SELECT id FROM product_category WHERE name = 'Papelaria')),
(UUID(), 'Mochila Escolar Premium', (SELECT id FROM product_brand WHERE name = 'Tramontina'), 'Mochila espacosa com compartimentos multiplos. Ideal para material escolar, notebooks e itens pessoais. Alcas acolchoadas e ajuste confortavel. Material resistente a agua e facil de limpar. Design moderno e funcional.', 'Escolar Aventura', 'Preto', '30x45x20 cm', 0.80, 50.00, 99.99, 1, 89.99, (SELECT id FROM product_category WHERE name = 'Material Escolar')),
(UUID(), 'Apontador com Deposito Grande', (SELECT id FROM product_brand WHERE name = 'Faber-Castell'), 'Apontador com lamina de alta qualidade e deposito grande para residuos. Design ergonomico, facilita o apontar de lapis de diversos tamanhos. Ideal para uso escolar e artistico. Pratico e facil de usar.', 'Utensilios Praticos', 'Vermelho', '3x2x2 cm', 0.02, 3.00, 6.00, 0, NULL, (SELECT id FROM product_category WHERE name = 'Material Escolar'));

INSERT INTO grade (uuid, name, school_id) VALUES
(UUID(), 'Maternal I A', (SELECT id FROM school WHERE name = 'Centro Educacional Estrelinha')),
(UUID(), '1 Ano Fundamental B', (SELECT id FROM school WHERE name = 'Escola Municipal Sol Nascente')),
(UUID(), '9 Ano Fundamental C', (SELECT id FROM school WHERE name = 'Escola Municipal Sol Nascente')),
(UUID(), '1 Ano Ensino Medio D', (SELECT id FROM school WHERE name = 'Colegio Alfa Prime'));

INSERT INTO cart (uuid, user_id, created_at, updated_at) VALUES
(UUID(), (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com'), NOW(), NOW()),
(UUID(), (SELECT id FROM `user` WHERE email = 'ana.silva@example.com'), NOW(), NOW());

INSERT INTO product_list (uuid, school_id, grade_id, name, description, price, academic_year) VALUES
(UUID(), (SELECT id FROM school WHERE name = 'Escola Municipal Sol Nascente'), (SELECT id FROM grade WHERE name = '1 Ano Fundamental B'), 'Pacote Basico EF1 2025', 'Materiais essenciais para o 1o ano do ensino fundamental, incluindo lapis, cadernos e apontador. Tudo que seu filho precisa para um inicio de ano letivo. Este pacote foi cuidadosamente selecionado para atender as necessidades pedagogicas e praticas dos alunos. Garanta ja!', 150.00, '2025/2026'),
(UUID(), (SELECT id FROM school WHERE name = 'Colegio Alfa Prime'), (SELECT id FROM grade WHERE name = '1 Ano Ensino Medio D'), 'Kit Completo EM 2025 Alfa', 'Kit abrangente para o Ensino Medio, com mochila, canetas, e cadernos. Ideal para todas as disciplinas e para manter a organizacao e foco nos estudos. Inclui itens de alta qualidade e durabilidade para acompanhar o aluno durante todo o ano letivo. Maximize o desempenho!', 300.00, '2025/2026'),
(UUID(), (SELECT id FROM school WHERE name = 'Centro Educacional Estrelinha'), (SELECT id FROM grade WHERE name = 'Maternal I A'), 'Pacote Inicial Maternal I 2025', 'Materiais basicos para o Maternal I, com itens seguros e adequados para criancas pequenas. Inclui lapis de cor grossos e cadernos com capa macia. Design divertido para estimular o aprendizado e a criatividade dos pequenos.', 100.00, '2025/2026');

INSERT INTO inventory (uuid, product_id, quantity_available, quantity_reserved, status, created_at, last_updated_at) VALUES
(UUID(), (SELECT id FROM product WHERE name = 'Lapis de Cor 12 Cores Classic'), 100, 0, 'AVAILABLE', NOW(), NOW()),
(UUID(), (SELECT id FROM product WHERE name = 'Caneta Esferografica Azul Cristal'), 500, 0, 'AVAILABLE', NOW(), NOW()),
(UUID(), (SELECT id FROM product WHERE name = 'Caderno Espiral 96 Fls Universit'), 200, 0, 'AVAILABLE', NOW(), NOW()),
(UUID(), (SELECT id FROM product WHERE name = 'Mochila Escolar Premium'), 50, 0, 'AVAILABLE', NOW(), NOW()),
(UUID(), (SELECT id FROM product WHERE name = 'Apontador com Deposito Grande'), 150, 0, 'AVAILABLE', NOW(), NOW());

INSERT INTO product_order (uuid, order_type_id, user_id, address_id, order_amount, shipping_amount, eta, created_at, updated_at) VALUES
(UUID(), (SELECT id FROM order_type WHERE name = 'Usuario final'), (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com'), (SELECT id FROM address WHERE label = 'Casa Lucas Principal'), 175.00, 25.00, '2025-08-10', NOW(), NOW()),
(UUID(), (SELECT id FROM order_type WHERE name = 'Compra interna'), (SELECT id FROM `user` WHERE email = 'ana.silva@example.com'), (SELECT id FROM address WHERE label = 'Apto Ana Vista Livre'), 310.00, 10.00, '2025-08-15', NOW(), NOW());

INSERT INTO cart_item (uuid, cart_id, product_id, quantity, price_at_time_of_addition) VALUES
(UUID(), (SELECT id FROM cart WHERE user_id = (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com') LIMIT 1), (SELECT id FROM product WHERE name = 'Lapis de Cor 12 Cores Classic'), 2, 24.90),
(UUID(), (SELECT id FROM cart WHERE user_id = (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com') LIMIT 1), (SELECT id FROM product WHERE name = 'Caderno Espiral 96 Fls Universit'), 3, 15.99),
(UUID(), (SELECT id FROM cart WHERE user_id = (SELECT id FROM `user` WHERE email = 'ana.silva@example.com') LIMIT 1), (SELECT id FROM product WHERE name = 'Mochila Escolar Premium'), 1, 99.99);

INSERT INTO order_items (uuid, product_order_id, product_id, quantity, price_at_time_of_order) VALUES
(UUID(), (SELECT id FROM product_order WHERE user_id = (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com') AND order_amount = 175.00 LIMIT 1), (SELECT id FROM product WHERE name = 'Lapis de Cor 12 Cores Classic'), 1, 24.90),
(UUID(), (SELECT id FROM product_order WHERE user_id = (SELECT id FROM `user` WHERE email = 'lucas.dom@example.com') AND order_amount = 175.00 LIMIT 1), (SELECT id FROM product WHERE name = 'Apontador com Deposito Grande'), 2, 6.00),
(UUID(), (SELECT id FROM product_order WHERE user_id = (SELECT id FROM `user` WHERE email = 'ana.silva@example.com') AND order_amount = 310.00 LIMIT 1), (SELECT id FROM product WHERE name = 'Mochila Escolar Premium'), 1, 89.99),
(UUID(), (SELECT id FROM product_order WHERE user_id = (SELECT id FROM `user` WHERE email = 'ana.silva@example.com') AND order_amount = 310.00 LIMIT 1), (SELECT id FROM product WHERE name = 'Caneta Esferografica Azul Cristal'), 5, 2.50);

INSERT INTO product_list_item (uuid, product_list_id, product_id, quantity) VALUES
(UUID(), (SELECT id FROM product_list WHERE name = 'Pacote Basico EF1 2025' LIMIT 1), (SELECT id FROM product WHERE name = 'Lapis de Cor 12 Cores Classic'), 1),
(UUID(), (SELECT id FROM product_list WHERE name = 'Pacote Basico EF1 2025' LIMIT 1), (SELECT id FROM product WHERE name = 'Caderno Espiral 96 Fls Universit'), 4),
(UUID(), (SELECT id FROM product_list WHERE name = 'Kit Completo EM 2025 Alfa' LIMIT 1), (SELECT id FROM product WHERE name = 'Mochila Escolar Premium'), 1),
(UUID(), (SELECT id FROM product_list WHERE name = 'Kit Completo EM 2025 Alfa' LIMIT 1), (SELECT id FROM product WHERE name = 'Caneta Esferografica Azul Cristal'), 10),
(UUID(), (SELECT id FROM product_list WHERE name = 'Pacote Inicial Maternal I 2025' LIMIT 1), (SELECT id FROM product WHERE name = 'Apontador com Deposito Grande'), 1);

COMMIT;