<%-- 
    Document   : payin
    Created on : Jun 8, 2023, 1:10:40 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body class="animsition">

    <div class="row" style="margin-top: 90px!important">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <div class="card">
                <div class="card-header">Credit Card</div>
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center title-2">Pay Invoice</h3>
                    </div>
                    <hr>
                    <form action="" method="post" novalidate="novalidate">
                        <div class="form-group">
                            <label for="typeCard" class="control-label mb-1">Type of Card</label>
                            <div class="input-group">
                                  <span class="input-group-addon">
                                        <i class="fab fa-cc-visa"></i>
                                        <i class="fab fa-cc-mastercard"></i>
                                        <i class="fab fa-cc-amex"></i>
                                    </span>
                                <select id="typeCard" name="typeCard" class="form-control" aria-required="true" aria-invalid="false">
                                    <option value="visa" selected><i class="fab fa-cc-visa"></i> Visa</option>
                                    <option value="mastercard"><i class="fab fa-cc-mastercard"></i> Mastercard</option>
                                    <option value="amex"><i class="fab fa-cc-amex"></i> American Express</option>
                                    <option value="discover"><i class="fab fa-cc-discover"></i> Discover</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="cc-payment" class="control-label mb-1">Payment amount</label>
                            <input id="cc-pament" name="cc-payment" type="text" class="form-control" aria-required="true" aria-invalid="false" value="100.00">
                        </div>
                        <div class="form-group has-success">
                            <label for="cc-name" class="control-label mb-1">Name on card</label>
                            <input id="cc-name" name="cc-name" type="text" class="form-control cc-name valid" data-val="true" data-val-required="Please enter the name on card" autocomplete="cc-name" aria-required="true" aria-invalid="false" aria-describedby="cc-name-error">
                            <span class="help-block field-validation-valid" data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
                        </div>
                        <div class="form-group">
                            <label for="cc-number" class="control-label mb-1">Card number</label>
                            <input id="cc-number" name="cc-number" type="tel" class="form-control cc-number identified visa" value="" data-val="true" data-val-required="Please enter the card number" data-val-cc-number="Please enter a valid card number" autocomplete="cc-number">
                            <span class="help-block" data-valmsg-for="cc-number" data-valmsg-replace="true"></span>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="cc-exp" class="control-label mb-1">Expiration</label>
                                    <input id="cc-exp" name="cc-exp" type="tel" class="form-control cc-exp" value="" data-val="true" data-val-required="Please enter the card expiration" data-val-cc-exp="Please enter a valid month and year" placeholder="MM / YY" autocomplete="cc-exp">
                                    <span class="help-block" data-valmsg-for="cc-exp" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <div class="col-6">
                                <label for="x_card_code" class="control-label mb-1">Security code</label>
                                <div class="input-group">
                                    <input id="x_card_code" name="x_card_code" type="tel" class="form-control cc-cvc" value="" data-val="true" data-val-required="Please enter the security code" data-val-cc-cvc="Please enter a valid security code" autocomplete="off">

                                </div>
                            </div>
                        </div>
                        <div>
                            <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                <i class="fa fa-lock fa-lg"></i>&nbsp;
                                <span id="payment-button-amount">Pay $100.00</span>
                                <span id="payment-button-sending" style="display:none;">Sending…</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-3"></div>
    </div>

</body>

