import socket
import time


def scan_udp_ports(host: str, start_port: int, end_port: int):
    start_time = time.time()
    ports = []

    for port in range(start_port, end_port + 1):
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            sock.settimeout(1)
            sock.sendto(b"", (host, port))
            sock.recvfrom(1024)

            try:
                service = socket.getservbyport(port, "udp")
            except:
                service = "unknown"

            ports.append({
                "portNumber": port,
                "serviceName": service.upper(),
                "state": "OPEN"
            })

        except Exception:
            pass
        finally:
            sock.close()

    scan_time_ms = int((time.time() - start_time) * 1000)

    return {
        "host": host,
        "protocol": "UDP",
        "ports": ports,
        "scan_time_ms": scan_time_ms,
        "success": True,
        "message": "UDP scan complete"
    }
