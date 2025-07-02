package com.example.ecommerce.enumerations;

/**
 * Estados por los que puede atravesar un Order
 */
public enum OrderStatus {
    /**
     * Pedido creado pero aún no pagado
     */
    PENDING,

    /**
     * Pago autorizado / recibido
     */
    PAID,

    /**
     * Preparado y en ruta de envío
     */
    SHIPPED,

    /**
     * Entregado al cliente
     */
    DELIVERED,

    /**
     * Pedido cancelado (por el usuario o el sistema)
     */
    CANCELLED
}