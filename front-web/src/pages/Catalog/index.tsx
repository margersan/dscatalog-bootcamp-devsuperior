import React from 'react';
import './styles.scss';
import ProductsCard from './components/ProductsCard';

const Catalog = () => (
    <div className="catalog-container">
        <h1 className="catalog-title">
            Catálogo de produtos
        </h1>
        <div className="catalog-products">
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
            <ProductsCard />
        </div>
    </div>
);

export default Catalog;