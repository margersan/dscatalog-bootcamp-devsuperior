import React from 'react';
import {ReactComponent as ProductImage} from '../../../../core/assets/images/product.svg'
import './styles.scss';

const ProductsCard = () => (
    <div className="products-card card-base border-radius-10">
        <ProductImage />
        <div className="product-info"> 
            <h6 className='product-name'>
                Computador Desktop - Intel Core i7
            </h6>
            <div className="product-price-container">
                <span className="product-price-currency">R$</span>
                <h3 className='product-price'>2.779,00</h3>
            </div>
        </div>
    </div>

);

export default ProductsCard;