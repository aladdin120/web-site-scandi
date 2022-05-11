BEGIN;


CREATE TABLE IF NOT EXISTS public."сlient"
(
    client_id bigint NOT NULL,
    c_name character varying(255) NOT NULL,
    c_address character varying(255) NOT NULL,
    c_phone_number character varying(255) NOT NULL,
    PRIMARY KEY (client_id)
    );

CREATE TABLE IF NOT EXISTS public."orders"
(
    id_order bigint NOT NULL,
    client_id bigint NOT NULL,
    o_date_priem character varying(255) NOT NULL,
    o_price bigint NOT NULL,
    o_predopl bigint NOT NULL,
    o_tel_number character varying(255) NOT NULL,
    PRIMARY KEY (id_order)
    );

CREATE TABLE IF NOT EXISTS public."product"
(
    id_product bigint NOT NULL,
    p_name character varying(255) NOT NULL,
    p_count character varying(255) NOT NULL,
    PRIMARY KEY (id_product)
    );

CREATE TABLE IF NOT EXISTS public."set"
(
    set_id bigint NOT NULL,
    s_name character varying(255) NOT NULL,
    s_price bigint,
    material_id bigint NOT NULL,
    detail_id bigint NOT NULL,
    furniture_id bigint,
    s_mat_count bigint NOT NULL,
    s_details_count bigint NOT NULL,
    s_furniture_count bigint,
    s_size_len bigint,
    s_size_wid bigint,
    s_size_height bigint,
    PRIMARY KEY (set_id)
    );

CREATE TABLE IF NOT EXISTS public."product_order"
(
    "product_id_product" bigint NOT NULL,
    "order_id_order" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."set_Product"
(
    "set_set_id" bigint NOT NULL,
    "product_id_product" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."material"
(
    id_material bigint NOT NULL,
    set_id bigint NOT NULL,
    m_name character varying(255) NOT NULL,
    m_price character varying(255) NOT NULL,
    m_colour character varying(255) NOT NULL,
    PRIMARY KEY (id_material)
    );

CREATE TABLE IF NOT EXISTS public."details"
(
    id_detail bigint NOT NULL,
    set_id bigint NOT NULL,
    d_name character varying(255) NOT NULL,
    d_size_len bigint NOT NULL,
    d_size_wed bigint NOT NULL,
    d_size_height bigint NOT NULL,
    d_price bigint NOT NULL,
    d_um bigint NOT NULL,
    PRIMARY KEY (id_detail)
    );

CREATE TABLE IF NOT EXISTS public."furniture"
(
    furniture_id bigint NOT NULL,
    set_id bigint NOT NULL,
    f_name character varying(255) NOT NULL,
    f_colour character varying(255) NOT NULL,
    f_price character varying(255) NOT NULL,
    f_city character varying(255) NOT NULL,
    PRIMARY KEY (furniture_id)
    );

CREATE TABLE IF NOT EXISTS public."furchase"
(
    purchase_id bigint NOT NULL,
    pur_mat_count bigint NOT NULL,
    pur_fur_count bigint NOT NULL,
    pur_det_count bigint NOT NULL,
    sup_id bigint NOT NULL,
    supplier_id bigint NOT NULL,
    PRIMARY KEY (purchase_id)
    );

CREATE TABLE IF NOT EXISTS public."purchase_material"
(
    "purchase_purchase_id" bigint NOT NULL,
    "material_id_material" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."purchase_details"
(
    "purchase_purchase_id" bigint NOT NULL,
    "details_id_detail" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."purchase_furniture"
(
    "purchase_purchase_id" bigint NOT NULL,
    "furniture_furniture_id" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."supply"
(
    id bigint NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public."supply_Material"
(
    "supply_id" bigint NOT NULL,
    "material_id_material" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."supply_details"
(
    "supply_id" bigint NOT NULL,
    "details_id_detail" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."supply_furniture"
(
    "supply_id" bigint NOT NULL,
    "furniture_furniture_id" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public."supplier"
(
    supplier_id bigint NOT NULL,
    sup_address character varying(255) NOT NULL,
    sup_tel_number character varying(255) NOT NULL,
    PRIMARY KEY (supplier_id)
    );
END;