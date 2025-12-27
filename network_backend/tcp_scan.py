import socket
import time


def tcp_scan(host: str, start_port: int, end_port: int):
    start_time = time.time()
    ports = []

    for port in range(start_port, end_port + 1):
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.settimeout(1)
            result = sock.connect_ex((host, port))
            sock.close()

            if result == 0:
                try:
                    service = socket.getservbyport(port, "tcp")
                except:
                    service = "unknown"

                ports.append({
                    "portNumber": port,
                    "serviceName": service.upper(),
                    "state": "OPEN"
                })

        except Exception:
            pass

    scan_time_ms = int((time.time() - start_time) * 1000)

    return {
        "host": host,
        "protocol": "TCP",
        "ports": ports,
        "scan_time_ms": scan_time_ms,
        "success": True,
        "message": "TCP scan complete"
    }
