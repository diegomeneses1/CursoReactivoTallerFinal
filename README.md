# CursoReactivoTallerFinal

Este es un microservicio desarrollado en spring webflux con una base de datos mongodb, proyecto reactivo.

- ### Microcervicio de Pagos externo
Se simula el consumo de un servicio externo a travéz del controlador PaymentController y la interfaz IPaymentRestClient, se simula un comportamiento básico donde se valida el documento del usuario, si es par procesa el pago, en caso contrario devuelve una excepción

- ### Usuarios

Obtiene todos los usuarios:

url = "http://localhost:8080/users"

payload = {}
headers = {
'Authorization': '••••••'
}

response = requests.request("GET", url, headers=headers, data=payload)

Crear un usuario:

rl = "http://localhost:8080/users"

payload = json.dumps({
"name": "",
"balance": -300
})
headers = {
'Content-Type': 'application/json',
'Authorization': '••••••'
}

response = requests.request("POST", url, headers=headers, data=payload)

Actualizar el balance de un usuario:

url = "http://localhost:8080/users/66f5de36827f686e36cc0886/balance"

payload = json.dumps({
"balance": 1000
})
headers = {
'Content-Type': 'application/json',
'Authorization': '••••••'
}

response = requests.request("PUT", url, headers=headers, data=payload)

- ### CashOuts
En este servicio se pueden listar, obtener por id y crear siguiendo un flujo específico y validaciones

url = "http://localhost:8080/cashout"

payload = "{\r\n  \"userId\": \"1\",\r\n  \"amount\": 449.0\r\n}"
headers = {}

response = requests.request("POST", url, headers=headers, data=payload)

Obtener por usuarios
url = "http://localhost:8080/cashout/user/66f5de36827f686e36cc0886"

payload = {}
headers = {
'Authorization': '••••••'
}

response = requests.request("GET", url, headers=headers, data=payload)
