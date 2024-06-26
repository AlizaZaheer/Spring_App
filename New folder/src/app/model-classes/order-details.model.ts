export class OrderDetails{
    orderId:number;
    userId!:number;

    name!:string;
    email!:string;
    contact!:string;

    amount!:number;
    address!:string;
    city!:string;
    state!:string;
    zip!:string;
    distance!:number;

    paymentMethod!:string;
    paymentId!:string;
    orderDate!:Date;
    
}